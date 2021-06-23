package com.zj.study.derby.page;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.AccessFactoryGlobals;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.heap.Heap;
import org.apache.derby.impl.store.raw.data.BaseContainerHandle;
import org.apache.derby.impl.store.raw.data.DirectActions;
import org.apache.derby.impl.store.raw.data.StoredPage;

public class PageTest {

	public static void main(String[] args) throws Exception {
		ContainerKey ContainerKey = new ContainerKey(0, 16);
		PageKey pageKey = new PageKey(ContainerKey, 1);

		StoredPage sp = new StoredPage();
//		org.apache.derby.impl.store.raw.data.PageCreationArgs createParameter = null;
//		sp.createIdentity(pageKey, createParameter);
		Class<?> classType = sp.getClass();
		Method initalizeMethod = classType.getDeclaredMethod("initialize", null);
		initalizeMethod.setAccessible(true);
		initalizeMethod.invoke(sp, null);

		Method initalizeHeadersMethod = classType.getSuperclass().getSuperclass().getDeclaredMethod("initializeHeaders",
				new Class[] { Integer.TYPE });
		initalizeHeadersMethod.setAccessible(true);
		initalizeHeadersMethod.invoke(sp, 5);

		createPage(sp, pageKey);

		fillInIdentity(sp, pageKey);

		Field initialRowCountFeild = sp.getClass().getSuperclass().getDeclaredField("initialRowCount");
		initialRowCountFeild.setAccessible(true);
		initialRowCountFeild.set(sp, 0);

		BaseContainerHandle handler = new BaseContainerHandle(null, null, ContainerKey, new NoLocking(), 7);
		Field actionsSetFeild = handler.getClass().getDeclaredField("actionsSet");
		actionsSetFeild.setAccessible(true);
		actionsSetFeild.set(handler, new DirectActions());

		Field ownerFeild = sp.getClass().getSuperclass().getSuperclass().getDeclaredField("owner");
		ownerFeild.setAccessible(true);
		ownerFeild.set(sp, handler);

		sp.initPage(0, 4096);
		sp.setContainerRowCount(0);

		DataValueDescriptor[] control_row = new DataValueDescriptor[1];
		Heap heap = new Heap();
		setHeap(heap);
		control_row[0] = heap;

		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 1, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 2, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 3, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 4, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 5, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		sp.insertAtSlot(Page.FIRST_SLOT_NUMBER + 6, control_row, (FormatableBitSet) null, (LogicalUndo) null,
				Page.INSERT_OVERFLOW, AccessFactoryGlobals.HEAP_OVERFLOW_THRESHOLD);
		System.err.println(sp.toString());
	}

	private static void setHeap(Heap heap) throws Exception {
		Field heapFeild = heap.getClass().getDeclaredField("id");
		heapFeild.setAccessible(true);
		heapFeild.set(heap, new ContainerKey(0, 16));

		Field format_idsFeild = heap.getClass().getDeclaredField("format_ids");
		format_idsFeild.setAccessible(true);
		format_idsFeild.set(heap, new int[] { 266, 266 });

		Field conglom_format_idFeild = heap.getClass().getDeclaredField("conglom_format_id");
		conglom_format_idFeild.setAccessible(true);
		conglom_format_idFeild.set(heap, heap.getTypeFormatId());

		Field collation_idsFeild = heap.getClass().getDeclaredField("collation_ids");
		collation_idsFeild.setAccessible(true);
		collation_idsFeild.set(heap, new int[] { 266, 266 });
	}

	private static void fillInIdentity(StoredPage sp, PageKey pageKey) throws Exception {
		Method fillInIdentityMethod = sp.getClass().getSuperclass().getSuperclass().getDeclaredMethod("fillInIdentity",
				new Class[] { PageKey.class });
		fillInIdentityMethod.setAccessible(true);
		fillInIdentityMethod.invoke(sp, pageKey);
	}

	private static void createPage(StoredPage sp, PageKey pageKey) throws Exception, IllegalAccessException {
		Field spareSpaceFeild = sp.getClass().getDeclaredField("spareSpace");
		spareSpaceFeild.setAccessible(true);
		spareSpaceFeild.set(sp, 0);

		Field minimumRecordSizeFeild = sp.getClass().getDeclaredField("minimumRecordSize");
		minimumRecordSizeFeild.setAccessible(true);
		minimumRecordSizeFeild.set(sp, 12);

		Method setPageArrayMethod = sp.getClass().getSuperclass().getDeclaredMethod("setPageArray",
				new Class[] { Integer.TYPE });
		setPageArrayMethod.setAccessible(true);
		setPageArrayMethod.invoke(sp, 4096);

		Method cleanPageMethod = sp.getClass().getDeclaredMethod("cleanPage", null);
		cleanPageMethod.setAccessible(true);
		cleanPageMethod.invoke(sp);

		sp.setPageVersion(0L);

		Field nextIdFeild = sp.getClass().getDeclaredField("nextId");
		nextIdFeild.setAccessible(true);
		nextIdFeild.set(sp, RecordHandle.FIRST_RECORD_ID);

		Field generationFeild = sp.getClass().getDeclaredField("generation");
		generationFeild.setAccessible(true);
		generationFeild.set(sp, 0);

		Field prevGenerationFeild = sp.getClass().getDeclaredField("prevGeneration");
		prevGenerationFeild.setAccessible(true);
		prevGenerationFeild.set(sp, 0);

		Field bipLocationFeild = sp.getClass().getDeclaredField("bipLocation");
		bipLocationFeild.setAccessible(true);
		bipLocationFeild.set(sp, 0L);

		Method createOutStreamsMethod = sp.getClass().getDeclaredMethod("createOutStreams", null);
		createOutStreamsMethod.setAccessible(true);
		createOutStreamsMethod.invoke(sp);
	}

}
