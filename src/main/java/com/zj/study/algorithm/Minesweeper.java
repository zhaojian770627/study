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
		// sweeper.genNine(3);
		// System.out.println(sweeper.sudokuVerify(sweeper.genNineSpace()));
		sweeper.rotate(sweeper.createRotate());
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

	private int[][] genNineSpace() {
		int[][] ori = new int[][] { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
				{ 1, 9, 8, 3, 4, 2, 5, 6, 7 }, { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
				{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
				{ 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

		return ori;
	}

	private boolean sudokuVerify(int board[][]) {
		int num = board.length;

		for (int row = 0; row < num; row++) {
			int result_row = 0;
			int result_col = 0;
			int result_blk = 0;
			for (int col = 0; col < num; col++) {
				int temp = board[row][col];

				if ((result_row & (1 << (temp - 1))) == 0)
					result_row = result_row | 1 << (temp - 1);
				else
					return false;

				temp = board[col][row];
				if ((result_col & (1 << (temp - 1))) == 0)
					result_col = result_col | 1 << (temp - 1);
				else
					return false;

				int idx_row = (row / 3) * 3 + col / 3;
				int idx_col = (row % 3) * 3 + col % 3;
				temp = board[idx_row][idx_col];
				if ((result_blk & (1 << (temp - 1))) == 0)
					result_blk = result_blk | 1 << (temp - 1);
				else
					return false;
			}
		}

		return true;
	}

	private int[][] createRotate() {
		int[][] ori = new int[][] { { 0, 1, 2, 3, 4 }, { 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14 },
				{ 15, 16, 17, 18, 19 }, { 20, 21, 22, 23, 24 } };

		return ori;
	}

	/**
	 * 旋转数组
	 * 
	 * @param a
	 * @return
	 */
	private int[][] rotate(int[][] a) {
		int n = a.length;
		int[][] b = new int[a.length][a.length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++) {
				b[j][n - i - 1] = a[i][j];
			}

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}

		return b;
	}
}
