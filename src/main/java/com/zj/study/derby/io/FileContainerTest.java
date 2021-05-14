package com.zj.study.derby.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.zip.CRC32;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.reference.Property;
import org.apache.derby.iapi.reference.SQLState;
import org.apache.derby.iapi.services.io.ArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.impl.store.raw.data.AllocPage;
import org.apache.derby.shared.common.sanity.SanityManager;

public class FileContainerTest {
	private static final int CONTAINER_FORMAT_ID_SIZE = 4;
	protected static final int CHECKSUM_SIZE = 8;
	protected static final int CONTAINER_INFO_SIZE = CONTAINER_FORMAT_ID_SIZE + 4 + 4 + 4 + 4 + 2 + 2 + 8 + 8 + 8 + 8
			+ CHECKSUM_SIZE + 8 + 8;
	private static final int PRE_ALLOC_THRESHOLD = 8;
	private static final int MIN_PRE_ALLOC_SIZE = 1;
	private static final int DEFAULT_PRE_ALLOC_SIZE = 8;
	private static final int FILE_DROPPED = 0x1;
	private static final int FILE_COMMITTED_DROP = 0x2;

	private static final int FILE_REUSABLE_RECORDID = 0x8;

	// 私有变量
	byte[] containerInfo;
	private CRC32 checksum; // holder for the checksum
	protected AllocationCache allocCache;
	protected int pageSize; // size of my pages
	protected int spareSpace; // % space kept free on page in inserts
	protected int minimumRecordSize; // minimum space a record should
	protected short initialPages; // initial number of pages preallocated
	protected long firstAllocPageNumber; // first alloc page number
	protected long firstAllocPageOffset; // first alloc page offset
	protected long containerVersion; // the logged version number
	protected long estimatedRowCount; // value is changed unlogged
	/**
	 * The sequence number for reusable recordIds . As long as this number does not
	 * change, recordIds will be stable within the container.
	 **/
	private long reusableRecordIdSequenceNumber;

	protected boolean isDropped;
	protected boolean isCommittedDrop;
	protected boolean isReusableRecordId = false;

	protected LogInstant lastLogInstant; // last time this container
	private long lastUnfilledPage;
	private long lastAllocatedPage;
	private long estimatedPageCount;
	private int PreAllocThreshold; // how many pages before preallocation
	// kicks in, only stored in memory
	private int PreAllocSize; // how many pages to preallocate at once
	// only stored in memory
	private boolean bulkIncreaseContainerSize;// if true, the next addPage will
	// attempt to preallocate a larger
	// than normal number of pages.

	public static void main(String[] args) throws Exception {
		Properties conglomProperties = new Properties();

		conglomProperties.put(Property.PAGE_SIZE_PARAMETER, RawStoreFactory.PAGE_SIZE_STRING);

		conglomProperties.put(RawStoreFactory.PAGE_RESERVED_SPACE_PARAMETER,
				RawStoreFactory.PAGE_RESERVED_ZERO_SPACE_STRING);

		FileContainerTest test = new FileContainerTest();
		ContainerKey containerKey = new ContainerKey(0, 16);
		test.createIdent(containerKey, conglomProperties);

	}

	private void createIdent(ContainerKey containerKey, Properties conglomProperties) throws Exception {
		initContainerHeader(true);
		createInfoFromProp(conglomProperties);
		RandomAccessFile dataFie = new RandomAccessFile("D:\\temp\\c10.data", "rw");
		writeRAFHeader(containerKey, dataFie, true, true);
	}

	// initialize header information so this container object can be safely
	// reused as if this container object has just been new'ed
	private void initContainerHeader(boolean changeContainer) {
		if (containerInfo == null)
			containerInfo = new byte[CONTAINER_INFO_SIZE];

		if (checksum == null)
			checksum = new CRC32();
		else
			checksum.reset();

		if (allocCache == null)
			allocCache = new AllocationCache();
		else
			allocCache.reset();

		if (changeContainer) {
			pageSize = 0;
			spareSpace = 0;
			minimumRecordSize = 0;
		}

		initialPages = 1;
		firstAllocPageNumber = ContainerHandle.INVALID_PAGE_NUMBER;
		firstAllocPageOffset = -1;
		containerVersion = 0;
		estimatedRowCount = 0;
		reusableRecordIdSequenceNumber = 0;

		setDroppedState(false);
		setCommittedDropState(false);
		setReusableRecordIdState(false);

		// instance variables that are not stored on disk
		lastLogInstant = null;

		initializeLastInsertedPage(1);
		lastUnfilledPage = ContainerHandle.INVALID_PAGE_NUMBER;
		lastAllocatedPage = ContainerHandle.INVALID_PAGE_NUMBER;
		estimatedPageCount = -1;

		PreAllocThreshold = PRE_ALLOC_THRESHOLD;
		PreAllocSize = DEFAULT_PRE_ALLOC_SIZE;
		bulkIncreaseContainerSize = false;
	}

	protected void setDroppedState(boolean isDropped) {
		this.isDropped = isDropped;
	}

	protected void setCommittedDropState(boolean isCommittedDrop) {
		this.isCommittedDrop = isCommittedDrop;
	}

	protected void setReusableRecordIdState(boolean isReusableRecordId) {
		this.isReusableRecordId = isReusableRecordId;
	}

	private long lastInsertedPage[];
	private int lastInsertedPage_index;

	private synchronized void initializeLastInsertedPage(int size) {
		lastInsertedPage = new long[size];

		for (int i = lastInsertedPage.length - 1; i >= 0; i--)
			lastInsertedPage[i] = ContainerHandle.INVALID_PAGE_NUMBER;

		lastInsertedPage_index = 0;
	}

	private void createInfoFromProp(Properties createArgs) throws StandardException {
		// Need a TransactionController to get database/service wide properties.
		pageSize = 4096;

		// rather than throw error, just automatically set page size to
		// default if bad value given.
		if ((pageSize != 4096) && (pageSize != 8192) && (pageSize != 16384) && (pageSize != 32768)) {
			pageSize = RawStoreFactory.PAGE_SIZE_DEFAULT;
		}

		spareSpace = 0;

		PreAllocSize = 8;

		// RESOLVE - in the future, we will allow user to set minimumRecordSize
		// to be larger than pageSize, when long rows are supported.
		// if the createArgs is null, then the following method call
		// will get the system properties from the appropriete places.
		// we want to make sure minimumRecrodSize is set to at least
		// the default value MINIMUM_RECORD_SIZE_DEFAULT (12)
		// as set in rawStoreFactory.
		minimumRecordSize = 12;

		// For the following properties, do not check value set in global
		// properties, we only listen to what access has to say about them.
		//
		// whether or not container's recordIds can be reused
		// if container is to be created with a large number of pages
		if (createArgs != null) {
			String reusableRecordIdParameter = createArgs.getProperty(RawStoreFactory.PAGE_REUSABLE_RECORD_ID);
			if (reusableRecordIdParameter != null) {
				Boolean reusableRecordId = Boolean.parseBoolean(reusableRecordIdParameter);
				setReusableRecordIdState(reusableRecordId.booleanValue());
			}

			String containerInitialPageParameter = createArgs.getProperty(RawStoreFactory.CONTAINER_INITIAL_PAGES);
			if (containerInitialPageParameter != null) {
				initialPages = Short.parseShort(containerInitialPageParameter);
				if (initialPages > 1) {
					if (initialPages > RawStoreFactory.MAX_CONTAINER_INITIAL_PAGES)
						initialPages = RawStoreFactory.MAX_CONTAINER_INITIAL_PAGES;
				}
			}
		}
	}

	private void writeHeaderToArray(byte[] a) throws IOException {
		if (SanityManager.DEBUG)
			SanityManager.ASSERT(a.length >= CONTAINER_INFO_SIZE, "header won't fit in array");

		ArrayOutputStream a_out = new ArrayOutputStream(a);
		FormatIdOutputStream outStream = new FormatIdOutputStream(a_out);

		int status = 0;
		if (getDroppedState())
			status |= FILE_DROPPED;
		if (getCommittedDropState())
			status |= FILE_COMMITTED_DROP;
		if (isReusableRecordId())
			status |= FILE_REUSABLE_RECORDID;

		a_out.setPosition(0);
		a_out.setLimit(CONTAINER_INFO_SIZE);
		outStream.writeInt(116);
		outStream.writeInt(status);
		outStream.writeInt(pageSize);
		outStream.writeInt(spareSpace);
		outStream.writeInt(minimumRecordSize);
		outStream.writeShort(initialPages);
		outStream.writeShort(PreAllocSize); // write spare1
		outStream.writeLong(firstAllocPageNumber);
		outStream.writeLong(firstAllocPageOffset);
		outStream.writeLong(containerVersion);
		outStream.writeLong(estimatedRowCount);
		outStream.writeLong(reusableRecordIdSequenceNumber);
		outStream.writeLong(0); // Write spare3

		checksum.reset();
		checksum.update(a, 0, CONTAINER_INFO_SIZE - CHECKSUM_SIZE);

		// write the checksum to the array
		outStream.writeLong(checksum.getValue());

		a_out.clearLimit();
	}

	protected boolean getDroppedState() {
		return isDropped;
	}

	protected boolean getCommittedDropState() {
		return isCommittedDrop;
	}

	protected boolean isReusableRecordId() {
		return isReusableRecordId;
	}

	private void writeRAFHeader(Object identity, RandomAccessFile file, boolean create, boolean syncFile)
			throws IOException, StandardException {
		byte[] epage;
		epage = new byte[pageSize];
		writeHeader(identity, file, create, epage);
		file.getFD().sync();
	}

	protected void writeHeader(Object identity, RandomAccessFile file, boolean create, byte[] epage)
			throws IOException, StandardException {
		// write out the current containerInfo in the borrowed space to byte
		// array containerInfo
		writeHeaderToArray(containerInfo);

		// RESOLVE: get no wait on the page cache to see if allocation page is
		// there, if so, use that instead of making a new array and a static
		// function.
		try {
			AllocPage.WriteContainerInfo(containerInfo, epage, create);
		} catch (StandardException se) {
			throw StandardException.newException(SQLState.DATA_BAD_CONTAINERINFO_WRITE, se, identity);
		}

		// now epage has the containerInfo written inside it

		// force WAL - and check to see if database is corrupt or is frozen.
//		dataFactory.flush(lastLogInstant);
		if (lastLogInstant != null)
			lastLogInstant = null;

		// write it out
		writeAtOffset(file, epage, 0);
	}

	void writeAtOffset(RandomAccessFile file, byte[] bytes, long offset) throws IOException, StandardException {
		file.seek(offset);
		file.write(bytes);
	}
}
