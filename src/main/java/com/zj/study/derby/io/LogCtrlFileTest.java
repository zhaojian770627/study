package com.zj.study.derby.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;

/*
  		int format id  文件格式
		int obsolete log file version 废弃版本标志
		long the log instant (LogCounter) of the last completed checkpoint
		   (logfile counter, position) 日志顺序号
		int Derby major version 主版本
		int Derby minor version 次版本
		int subversion revision/build number
		byte Flags (beta flag (0 or 1), test durability flag (0 or 1))
		byte spare (0)
		byte spare (0)
		byte spare (0)
		long spare (value set to 0)
		long checksum for control data written 校验和
 */
public class LogCtrlFileTest {

	private static final int OBSOLETE_LOG_VERSION_NUMBER = 9;
	private static final byte IS_BETA_FLAG = 0x1;
	private static final byte IS_DURABILITY_TESTMODE_NO_SYNC_FLAG = 0x2;

	public static void main(String[] args) throws IOException {
		CRC32 checksum = new CRC32();

		int fid = 128;
		long value = 0;
		int onDiskMajorVersion = 10;
		int onDiskMinorVersion = 14;
		int BuildNumberAsInt = 1828579;

		ByteArrayOutputStream baos = new ByteArrayOutputStream(64);
		DataOutputStream daos = new DataOutputStream(baos);

		daos.writeInt(fid);

		// so that when this db is booted by 1.1x and 1.2x JBMS, a IOException
		// stack trace rather than some error message that tells
		// the user to delete the database will show up.
		daos.writeInt(OBSOLETE_LOG_VERSION_NUMBER);

		daos.writeLong(value);

		daos.writeInt(onDiskMajorVersion);
		daos.writeInt(onDiskMinorVersion);

		// For 2.0 beta we added the build number and the isBeta indication.
		// (5 bytes from our first spare long)
		daos.writeInt(BuildNumberAsInt);

		byte flags = 0;
//		flags |= IS_BETA_FLAG;

		// When database is booted with derby.system.durability=test,
		// this mode does not guarantee that
		// - database will recover
		// - committed transactions will not be lost
		// - database will be in a consistent state
		// Hence necessary to keep track of this state so we don't
		// waste time resolving issues in such cases.
		// wasDBInDurabilityTestModeNoSync has information if database was
		// previously booted at any time in this mode
//		flags |= IS_DURABILITY_TESTMODE_NO_SYNC_FLAG;
		daos.writeByte(flags);

		//
		// write some spare bytes after 2.0 we have 3 + 2(8) spare bytes.
		long spare = 0;

		daos.writeByte(0);
		daos.writeByte(0);
		daos.writeByte(0);
		daos.writeLong(spare);
		daos.flush();
		// write the checksum for the control data written
		checksum.reset();
		checksum.update(baos.toByteArray(), 0, baos.size());
		daos.writeLong(checksum.getValue());
		daos.flush();

		RandomAccessFile logctrl = new RandomAccessFile("D:\\temp\\log.ctrl", "rw");
		logctrl.seek(0);
		logctrl.write(baos.toByteArray());
		logctrl.getFD().sync();
		logctrl.close();
	}

}
