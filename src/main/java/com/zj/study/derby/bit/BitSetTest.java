package com.zj.study.derby.bit;

import org.apache.derby.iapi.services.io.FormatableBitSet;

public class BitSetTest {
	public static void main(String[] args) {
		System.out.println(FormatableBitSet.maxBitsForSpace(5));
		FormatableBitSet bitSet = new FormatableBitSet(8);
	}
}
