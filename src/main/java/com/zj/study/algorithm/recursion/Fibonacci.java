package com.zj.study.algorithm.recursion;

/**
 * 
 * 斐波那契数列
 * 
 * @author zhaojian
 *
 */
public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		System.out.println(fibonacci.one(7));
		System.out.println(fibonacci.two(7));
		System.out.println(fibonacci.three(7)[0]);
	}

	private int[] three(int num) {
		if (num <= 1)
			return new int[] { 1, 0 };

		int[] a = three(num - 1);
		return new int[] { a[0] + a[1], a[0] };
	}

	/**
	 * 循环法
	 * 
	 * @param num
	 * @return
	 */
	int two(int num) {
		int[] a = new int[] { 0, 1 };
		for (int i = 1; i <= num; i++) {
			a = new int[] { a[1], a[0] + a[1] };
		}
		return a[0];
	}

	/**
	 * 不好的求解方法,递归法
	 * 
	 * @param num
	 * @return
	 */
	int one(int num) {
		if (num <= 2)
			return 1;

		return one(num - 2) + one(num - 1);
	}

}
