package com.zj.study.nio;

import java.nio.ByteBuffer;

public class TestBuffer {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
		Byte b = buffer.get();
		System.out.println(buffer.toString());
	}

}
