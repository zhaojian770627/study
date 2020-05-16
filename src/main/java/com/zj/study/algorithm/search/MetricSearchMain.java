package com.zj.study.algorithm.search;

import cern.colt.Arrays;

public class MetricSearchMain {

	public static void main(String[] args) {
		int[][] m = new int[][] { { 1, 3, 5, 7, 9 }, { 2, 4, 8, 11, 22 }, { 6, 15, 21, 24, 25 },
				{ 13, 18, 26, 28, 30 } };
		MetricSearchMain main = new MetricSearchMain();
		System.out.println(Arrays.toString(main.searchOne(m, 55)));
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
}
