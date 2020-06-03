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
		System.out.println(main.sub2(a));
	}

	/**
	 * 暴力枚举法
	 * 
	 * @param a
	 * @return
	 */
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

	/**
	 * 分治法
	 * 
	 * @param a
	 * @return
	 */
	private int sub2(int[] a) {
		return sub2_helper(a, 0, a.length - 1);
	}

	private int sub2_helper(int[] a, int left, int right) {
		if (left == right)
			return a[left];

		int mid = (left + right) / 2;
		return Math.max(Math.max(sub2_helper(a, left, mid), sub2_helper(a, mid + 1, right)),
				maxcrossing(a, left, mid, right));
	}

	private int maxcrossing(int[] a, int left, int mid, int right) {
		int sum = 0;
		int left_sum = Integer.MIN_VALUE;

		for (int i = mid; i >= left; i--) {
			sum = sum + a[i];
			if (sum > left_sum)
				left_sum = sum;
		}

		sum = 0;
		int right_sum = Integer.MIN_VALUE;
		for (int i = mid + 1; i <= right; i++) {
			sum = sum + a[i];
			if (sum > right_sum)
				right_sum = sum;
		}

		return left_sum + right_sum;
	}

}
