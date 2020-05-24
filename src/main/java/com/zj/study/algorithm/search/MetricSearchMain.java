package com.zj.study.algorithm.search;

public class MetricSearchMain {

	public static void main(String[] args) {
		int[][] m = new int[][] { { 1, 3, 5, 7, 9 }, { 2, 4, 8, 11, 22 }, { 6, 15, 21, 24, 25 },
				{ 13, 18, 26, 28, 30 } };
		MetricSearchMain main = new MetricSearchMain();
//		System.out.println(Arrays.toString(main.searchOne(m, 55)));
		m = new int[][] { { 1, 4, 5 }, { 2, 8, 12 }, { 3, 22, 23 } };
		System.out.println(main.kthSmallest(m, 6));
	}

	/**
	 * 从二维矩阵中搜索一个值
	 * 
	 * r 向上移动 c 向右移动
	 * 
	 * @param m
	 * @param key
	 * @return
	 */
	int[] searchOne(int[][] m, int key) {
		int row = m.length;
		int col = m[0].length;

		int r = row - 1;
		int c = 0;

		while (r >= 0 && c < col) {
			if (key == m[r][c])
				return new int[] { r, c };

			if (key > m[r][c]) {
				c++;
			} else
				r--;
		}

		return new int[] { -1, -1 };
	}

	/**
	 * 先二分法找中间的数为第几大
	 * 
	 * @param m
	 * @param k
	 * @return
	 */
	private int kthSmallest(int[][] m, int k) {
		int row = m.length;
		int col = m[0].length;

		int L = m[0][0];
		int R = m[row - 1][col - 1];
		while (L < R) {
			int mid = L + (R - L) / 2;
			int temp = search_lower_than_mid(m, row, col, mid);
			if (temp < k)
				L = mid + 1;
			else
				R = mid;
		}
		return L;
	}

	private int search_lower_than_mid(int[][] m, int row, int col, int mid) {
		// 预先定位到 row行，0列，为起点
		int i = row - 1;
		int j = 0;

		int cnt = 0;

		while (i >= 0 && j < col) {
			if (m[i][j] <= mid) {
				j++;
				cnt += i + 1; // 默认第
			} else
				i--;
		}
		return cnt;

	}

}
