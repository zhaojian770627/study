package com.zj.study.algorithm;

import org.junit.Test;

public class zj01est {
	@Test
	/**
	 * 找到丢失的数字
	 */
	public void findLostNum() {
		// 采用异或法,假如5个数字[1,2,3,4,5],现在数字[1,2,4,5],3丢失,可以用下面算法找到
		System.out.println((1 ^ 2 ^ 3 ^ 4 ^ 5) ^ (1 ^ 2 ^ 4 ^ 5));
	}
}
