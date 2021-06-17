package com.zj.study.derby.format;

import java.io.IOException;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.impl.store.raw.data.StoredRecordHeader;

public class StoredRecordHeaderTest {
	public static void main(String[] args) throws IOException {
		DynamicByteArrayOutputStream output = new DynamicByteArrayOutputStream();
		StoredRecordHeader recordHeader = new StoredRecordHeader();
		recordHeader.setId(1);
		recordHeader.setNumberFields(2);
		recordHeader.setFirstField(0);
		recordHeader.write(output);

	}
}
