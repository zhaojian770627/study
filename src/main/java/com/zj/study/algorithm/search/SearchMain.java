package com.zj.study.algorithm.search;

public class SearchMain {

	public static void main(String[] args) {
		// int[] a = new int[] { 4, 3, 5, 7, 9, 2, 1, 6, 8, 99 };
		// SortMain sortMain = new SortMain();
		// sortMain.quickSort(a);
		//
		// System.out.println(Arrays.toString(a));
		// 旋转数组
		int[] b = new int[] { 99, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		SearchMain searchMain = new SearchMain();
		int key = 9;
		// 经典二分查找
		// int index = searchMain.binarySearch(b, 9);
//		System.out.println("key:" + key + " index:" + index);

		// 旋转数组查找
		System.out.println(searchMain.rotateBinaryMin(b));

	}

	/**
	 * 二分搜索模板
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	public int binarySearch(int[] a, int key) {
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

	/**
	 * 
	 * 旋转数组查找最小值
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	// { 1, 2, 3, 4, 5, 6, 7, 8, 9, 99 };
	public int rotateBinaryMin(int[] a) {
		int low = 0;
		int high = a.length - 1;
		// 这里要+1，因为里面没有对mid进行加1
		while (low + 1 < high) {
			int mid = (low + high) >>> 1;

			// 表明左边是排好序的
			if (a[mid] > a[low]) {
				// 最小值在右边
				if (a[mid] > a[high]) {
					low = mid;
				} else {
					break;
				}
			}

			// 右边是排好序的
			if (a[mid] < a[high]) {
				// 最小值在左边
				if (a[mid] < a[low]) {
					high = mid;
				} else
					break;
			}
		}

		return a[low] < a[high] ? a[low] : a[high];
	}

	// 旋转数组查找
	public int rotateBinarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		// 这里要+1，因为里面没有对mid进行加1
		while (low + 1 < high) {
			int mid = (low + high) >>> 1;

			// 表明左边是排好序的
			if (a[mid] > a[low]) {
				// 最小值在右边
				if (a[mid] > a[high]) {
					low = mid;
				} else {
					break;
				}
			}

			// 右边是排好序的
			if (a[mid] < a[high]) {
				if (a[mid] < a[low]) {
					high = mid;
				} else
					break;
			}
		}

		return a[low] < a[high] ? a[low] : a[high];
	}
}
