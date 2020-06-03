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
		System.out.println(Arrays.toString(lst.toArray()));
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
		int inv_right = countInvFast(right, Arrays.copyOfRange(a, middle, a.length));

		int count = merge(result, left.toArray(new Integer[] { 0 }), right.toArray(new Integer[] { 0 }));
		count += inv_left + inv_right;
		return count;
	}

	/**
	 * 在合并排序的基础上，进行判断，在某次合并中如下
	 * 
	 * left:[1, 20] right:[4, 5, 6]
	 * 
	 * left和right都是已经排好序的，如果left中的某一个大于right的某一个，则left后续的肯定大于right后续的 后续以此判断，确实比较精妙
	 * 
	 * @param result
	 * @param left
	 * @param right
	 * @return
	 */
	int merge(List<Integer> result, Integer[] left, Integer[] right) {
		System.out.println("--------------merge-------------------");
		System.out.println("left:" + Arrays.toString(left));
		System.out.println("right:" + Arrays.toString(right));

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

		Integer[] leftAry = Arrays.copyOfRange(left, i, left.length);
		Collections.addAll(result, leftAry);
		Integer[] rightAry = Arrays.copyOfRange(right, j, right.length);
		Collections.addAll(result, rightAry);
		return inv_count;
	}
}
