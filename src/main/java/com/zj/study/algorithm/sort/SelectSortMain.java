package com.zj.study.algorithm.sort;

public class SelectSortMain {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8, 99 };
		SelectSortMain main = new SelectSortMain();
		main.sort(a);

		print(a);
	}

	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	private void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}

			int t = a[i];
			a[i] = a[min];
			a[min] = t;
		}
	}
}
