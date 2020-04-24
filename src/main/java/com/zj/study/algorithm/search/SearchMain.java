package com.zj.study.algorithm.search;

import java.util.Arrays;

import com.zj.study.algorithm.sort.SortMain;

public class SearchMain {

	public static void main(String[] args) {
		int[] a = new int[] { 4, 3, 5, 7, 9, 2, 1, 6, 8, 99 };
		SortMain sortMain = new SortMain();
		sortMain.quickSort(a);

		System.out.println(Arrays.toString(a));

		SearchMain searchMain = new SearchMain();
		int key = 9;
		int index = searchMain.binarySort(a, 9);

		System.out.println("key:" + key + " index:" + index);
	}

	/**
	 * 二分搜索模板
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	public int binarySort(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
