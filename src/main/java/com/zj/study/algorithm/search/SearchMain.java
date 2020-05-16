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
		// System.out.println("key:" + key + " index:" + index);

		// 旋转数组查找
		// System.out.println(searchMain.rotateBinaryMin(b));
		b = new int[] { 1, 4 };
		// System.out.println(searchMain.searchInsertPos(b, 5));
		// System.out.println(Arrays.toString(searchMain.searchRange(b, 2)));
		// System.out.println(searchMain.searchFirst(b, 7));

//		int[] houses = new int[] { 1, 2, 3 };
//		int[] heaters = new int[] { 2 };

//		int[] houses = new int[] { 1, 2, 3, 4 };
//		int[] heaters = new int[] { 1, 4 };

		int[] houses = new int[] { 1, 12, 23, 34 };
		int[] heaters = new int[] { 12, 24 };

		System.out.println(searchMain.findRadius(houses, heaters));
	}

	private int sqrt(int x) {
		if (x == 0)
			return 0;

		int low = 0;
		int high = x;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			int tmp = x / mid;
			if (mid == tmp)
				return mid;

			if (mid < tmp)
				low = mid + 1;
			else
				high = mid - 1;
		}

		return high;
	}

	/**
	 * 寻找管道
	 * 
	 * @param houses
	 * @param heaters
	 * @return
	 */
	private int findRadius(int[] houses, int[] heaters) {
		int ans = 0;
		for (int hourse : houses) {
			// 寻找插入位置
			int pos = searchInsertPos(heaters, hourse);
			int left = Integer.MAX_VALUE;
			int right = Integer.MAX_VALUE;
			if (pos < heaters.length) {
				if (heaters[pos] == hourse) {
					left = 0;
					right = 0;
				} else {
					if (pos == 0)
						left = Integer.MAX_VALUE;
					else
						left = hourse - heaters[pos - 1];

					right = heaters[pos] - hourse;
				}
			} else {
				left = hourse - heaters[pos - 1];
				right = Integer.MAX_VALUE;
			}
//			System.out.println("left:" + left + " right:" + right);
			ans = Math.max(ans, Math.min(left, right));
//			System.out.println("ans:" + ans);
		}

		return ans;
	}

	/**
	 * 
	 * 在流中搜索一个元素
	 * 
	 * @param as
	 * @param key
	 * @return
	 */
	public int searchFirst(int[] as, int key) {
		int length = as.length;

		int left = 0;
		int right = 1;

		while (right < length) {
			if (as[right] < key) {
				left = right;
				right = 2 * right;
			} else
				break;

			if (right > length) {
				right = length - 1;
				break;
			}
		}

		if (right < length) {
			int b[] = new int[right - left + 1];
			System.arraycopy(as, left, b, 0, right - left + 1);
			int pos = searchRange(b, key)[0];
			if (pos != -1)
				return left + pos;
		}
		return -1;
	}

	/**
	 * 
	 * 寻找一个数的起止范围
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	public int[] searchRange(int[] a, int key) {
		// search left
		int low = 0;
		int high = a.length - 1;
		// 这里要+1，因为里面没有对mid进行加1,寻找最小的值
		while (low + 1 < high) {
			int mid = (low + high) >>> 1;

			if (a[mid] == key) {
				high = mid;
			} else if (a[mid] < key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		int lpos;
		if (a[low] == key) {
			lpos = low;
		} else if (a[high] == key) {
			lpos = high;
		} else {
			return new int[] { -1, -1 };
		}

		low = 0;
		high = a.length - 1;
		while (low + 1 < high) {
			int mid = (low + high) >>> 1;

			if (a[mid] == key) {
				low = mid;
			} else if (a[mid] < key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		int rpos;
		if (a[high] == key) {
			rpos = high;
		} else if (a[low] == key) {
			rpos = low;
		} else {
			return new int[] { -1, -1 };
		}

		return new int[] { lpos, rpos };
	}

	// 寻找插入位置
	public int searchInsertPos(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		while (low < high - 1) {
			int mid = (low + high) >>> 1;
			if (key < a[mid]) {
				high = mid - 1;
			} else if (key > a[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		if (a[low] >= key)
			return low;

		if (a[high] >= key)
			return high;

		return high + 1;
	}

	/**
	 * 
	 * 旋转数组查找最小值
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	// { 5, 6, 7, 8, 9, 99,1, 2, 3, 4 };
	public int rotateBinarySearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		// 这里要+1，因为里面没有对mid进行加1
		while (low < high) {
			int mid = (low + high) >>> 1;

			if (a[mid] == key)
				return mid;

			// 判断是否是排好序的
			if (a[low] < a[mid]) {
				if (a[low] <= key && key < a[mid])
					high = mid - 1;
				else
					low = mid + 1;
			} else {
				if (a[low] < key && key > a[mid])
					high = mid - 1;
				else
					low = mid + 1;
			}
		}

		System.out.println("low:" + low + " high:" + high);
		if (a[low] == key)
			return low;

		if (a[high] == high)
			return high;
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
	// { 5, 6, 7, 8, 9, 99,1, 2, 3, 4 };
	public int rotateBinaryMin(int[] a) {
		int low = 0;
		int high = a.length - 1;
		// 这里要+1，因为里面没有对mid进行加1
		while (low + 1 < high) {
			// 判断是否是排好序的
			if (a[low] < a[high])
				return a[low];

			int mid = (low + high) >>> 1;

			if (a[mid] >= a[low])
				low = mid + 1;
			else
				high = mid;
		}

		return a[low] < a[high] ? a[low] : a[high];
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
}
