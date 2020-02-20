package com.zj.study.framework.jvm;

/**
 * 测试对象在Java内存中的分配
 * 
 * -Xms20m -Xmx20m -XX:+PrintGCDetails -Xmn2m -XX:SurvivorRatio=2
 * 
 * @author zhaojian
 *
 */
public class NewSize {

	public static void main(String[] args) {
		int cap = 1 * 1024 * 1024;// 1M
		byte[] b1 = new byte[cap];
		byte[] b2 = new byte[cap];
		byte[] b3 = new byte[cap];
		byte[] b4 = new byte[cap];
		byte[] b5 = new byte[cap];
		byte[] b6 = new byte[cap];
		byte[] b7 = new byte[cap];
		byte[] b8 = new byte[cap];
		byte[] b9 = new byte[cap];
		byte[] b10 = new byte[cap];
	}

}
