package com.zj.study.algorithm.divideconquer;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		int[] ary = { 1, 3, 5, 7, 2, 4, 6, 8 };
//		main.shuffleArray(ary, 0, ary.length - 1);

//		int[] height = { 3, 1, 2, 5, 1 };
		int[] height = { 1, 1, 8 };
		System.out.println(main.minSteps(height));
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

	public int minSteps(int[] height) {
		return minStepHelper(height, 0, height.length, 0);
	}

	int minStepHelper(int[] height, int left, int right, int h) {
		if (left >= right)
			return 0;

		int m = left;
		for (int i = left; i < right; i++)
			if (height[i] < height[m])
				m = i;

		System.out.println("----------------------------------------------");
		System.out.println("left:" + left + " right:" + right + " h:" + h);
		System.out.println("right-left:" + (right - left));
		int m2 = minStepHelper(height, left, m, height[m]);
		System.out.println("m2:" + m2);
		int m3 = minStepHelper(height, m + 1, right, height[m]);
		System.out.println("height[m]:" + height[m]);

		return Math.min(right - left, m2 + m3 + height[m] - h);

	}
}
