package com.zj.study.algorithm.search;

/**
 * 
 * 寻找最长子序列
 * 
 * @author zhaojianc
 *
 */
public class MaxSumSubMain {

	public static void main(String[] args) {
		int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		MaxSumSubMain main = new MaxSumSubMain();
		System.out.println(main.sub1(a));
	}

	private int sub1(int[] a) {
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < a.length; i++) {
			int sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				if (sum > result)
					result = sum;
			}
		}
		return result;
	}

}
