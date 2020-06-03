package com.zj.study.algorithm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		Integer[] a = { 1, 20, 6, 4, 5 };
//		System.out.println(main.countInv(a));
		List<Integer> lst = new ArrayList<>();
		System.out.println(main.countInvFast(lst, a));
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

	private int countInvFast(List<Integer> result, Integer[] a) {
		if (a.length < 2) {
			Collections.addAll(result, a);
			return 0;
		}

		int middle = a.length / 2;
		List<Integer> left = new ArrayList<>();
		int inv_left = countInvFast(left, Arrays.copyOfRange(a, 0, middle));
		List<Integer> right = new ArrayList<>();
		int inv_right = countInvFast(right, Arrays.copyOfRange(a, middle, a.length - 1));

		int count = merge(result, left.toArray(new Integer[] { 0 }), right.toArray(new Integer[] { 0 }));
		count += inv_left + inv_right;
		return count;
	}

	int merge(List<Integer> result, Integer[] left, Integer[] right) {
		int i = 0, j = 0, inv_count = 0;
		while (i < left.length && j < right.length) {
			if (left[i] < right[j]) {
				result.add(left[i]);
				i++;
			} else if (right[j] < left[i]) {
				result.add(right[j]);
				j++;
				inv_count += (left.length - i);
			}
		}

		Integer[] leftAry = Arrays.copyOfRange(left, i, left.length - 1);
		Collections.addAll(result, leftAry);
		Integer[] rightAry = Arrays.copyOfRange(right, j, right.length - 1);
		Collections.addAll(result, rightAry);
		return inv_count;
	}
}
