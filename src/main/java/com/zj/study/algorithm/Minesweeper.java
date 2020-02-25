package com.zj.study.algorithm;

import java.util.Random;

/**
 * 
 * 扫雷程序
 * 
 * @author zhaojian
 *
 */
public class Minesweeper {

	private void gen(int m, int n, int p) {
		int[][] board = new int[m + 2][n + 2];
		Random r = new Random();
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				int rv = r.nextInt(10);
				if (rv > p)
					board[i][j] = -1;
			}
		}

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (board[i][j] == -1)
					System.out.print("*");
				else
					System.out.print(".");
			}
			System.out.println();
		}

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (board[i][j] != -1) {
					for (int ii = i - 1; ii <= i + 1; ii++)
						for (int jj = j - 1; jj <= j + 1; jj++) {
							if (board[ii][jj] == -1) {
								board[i][j]++;
							}
						}
				}
			}
		}

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (board[i][j] == -1)
					System.out.print("*");
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Minesweeper sweeper = new Minesweeper();
		// sweeper.gen(10, 10, 8);

		sweeper.genNine(3);
	}

	/**
	 * 生成九宫格算法
	 * 
	 * @param n
	 */
	private void genNine(int n) {
		int[][] board = new int[n][n];
		int row = n - 1;
		int col = (n - 1) / 2;

		board[row][col] = 1;

		for (int i = 1; i < n * n; i++) {
			int tryrow = (row + 1) % n;
			int trycol = (col + 1) % n;

			// 计算下个位置
			if (board[tryrow][trycol] == 0) {
				row = tryrow;
				col = trycol;
			} else {
				row = (row - 1 + n) % n;
			}

			board[row][col] = i + 1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
