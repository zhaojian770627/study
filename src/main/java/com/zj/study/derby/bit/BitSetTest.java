package com.zj.study.derby.bit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;

public class BitSetTest {
	public static void main(String[] args) throws IOException {
		File file = new File("d:\\temp\\bitset.data");
		FileOutputStream out = new FileOutputStream(file);
		FormatIdOutputStream outStream = new FormatIdOutputStream(out);
		FormatableBitSet bitSet = new FormatableBitSet(8);
		bitSet.writeExternal(outStream);
		System.out.println(bitSet.toString());
		outStream.close();
	}
}
