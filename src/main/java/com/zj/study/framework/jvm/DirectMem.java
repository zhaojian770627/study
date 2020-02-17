package com.zj.study.framework.jvm;

import java.nio.ByteBuffer;

/**
 * 测试直接内存溢出
 * 
 * -Xmx10m -XX:MaxDirectMemorySize=10m
 * 
 * @author zhaojian
 *
 */
public class DirectMem {

	public static void main(String[] args) {
		ByteBuffer.allocateDirect(1024 * 1024 * 14);
	}

}
