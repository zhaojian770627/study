package com.zj.study.derby.format;

import java.io.IOException;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.impl.store.raw.data.StoredRecordHeader;

public class StoredRecordHeaderTest {
	public static void main(String[] args) throws IOException {
		DynamicByteArrayOutputStream output = new DynamicByteArrayOutputStream();
		StoredRecordHeader storeRecordHeader = new StoredRecordHeader();
		storeRecordHeader.write(output);
		
	}
}
