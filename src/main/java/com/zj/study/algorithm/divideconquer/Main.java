package com.zj.study.algorithm.divideconquer;

import cern.colt.Arrays;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		int[] ary = { 1, 3, 5, 7, 2, 4, 6, 8 };
		main.shuffleArray(ary, 0, ary.length - 1);
	}

	/**
	 * Given an array of 2n elements in the following format { a1, a2, a3, a4, …..,
	 * an, b1, b2, b3, b4, …., bn }. The task is shuffle the array to {a1, b1, a2,
	 * b2, a3, b3, ……, an, bn } without using extra space
	 * 
	 */
	void shuffleArray(int[] a, int left, int right) {
		// If only 2 element, return
		if (right - left == 1)
			return;

		// Finding mid to divide the array
		int mid = (left + right) / 2;

		// Using temp for swapping first
		// half of second array
		int temp = mid + 1;

		// Mid is use for swapping second
		// half for first array
		int mmid = (left + mid) / 2;

		// Swapping the element
		for (int i = mmid + 1; i <= mid; i++) {
			int tv = a[i];
			a[i] = a[temp];
			a[temp] = tv;
			temp++;
		}

		// Recursively doing for
		// first half and second half
		shuffleArray(a, left, mid);
		shuffleArray(a, mid + 1, right);
	}
}
