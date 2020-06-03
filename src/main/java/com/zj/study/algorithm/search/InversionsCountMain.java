package com.zj.study.algorithm.search;

/**
 * 
 * 逆序对计数
 * 
 * @author zhaojianc
 *
 */
public class InversionsCountMain {

	public static void main(String[] args) {
		InversionsCountMain main = new InversionsCountMain();
		int[] a = { 1, 20, 6, 4, 5 };
		System.out.println(main.countInv(a));
	}

	// O(n^2)
	private int countInv(int[] arr) {
		int n = arr.length;
		int inv_count = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j])
					inv_count++;
			}
		return inv_count;
	}
}
