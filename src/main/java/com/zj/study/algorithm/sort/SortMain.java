package com.zj.study.algorithm.sort;

public class SortMain {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8, 99 };
//		int[] a = new int[] { 3, 1, 4 };
		SortMain main = new SortMain();
//		main.selectSort(a);
		main.inserSort(a);
		print(a);
	}

	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	/**
	 * 选择排序
	 * 
	 * @param a
	 */
	private void selectSort(int[] a) {
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

	/**
	 * 
	 * 插入排序
	 * 
	 * @param a
	 */
	void inserSort(int[] a) {
		// 两层巡皇
		for (int i = 0; i < a.length; i++) {
			int j = i;
			for (; j > 0; j--) {
				if (a[i] < a[j - 1])
					continue;
				else
					break;
			}

			int t = a[i];
			for (int k = i; k > j; k--) {
				a[k] = a[k - 1];
			}
			a[j] = t;
		}
	}

	/**
	 * 希尔排序
	 * 
	 * @param a
	 */
	void shellSort(int[] a) {

	}

	/**
	 * 计数排序
	 * 
	 * @param a
	 */
	void countSort(int[] a) {

	}
}
