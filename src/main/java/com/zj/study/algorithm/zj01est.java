package com.zj.study.algorithm;

import java.util.Random;

import org.junit.Test;

public class zj01est {
	@Test
	/**
	 * 找到丢失的数字
	 */
	public void findLostNum() {
		// 采用异或法,假如5个数字[1,2,3,4,5],现在数字[1,2,4,5],3丢失,可以用下面算法找到
		System.out.println((1 ^ 2 ^ 3 ^ 4 ^ 5) ^ (1 ^ 2 ^ 4 ^ 5));
	}

	private int[] createCards(int length) {
		int[] cards = new int[length];
		for (int i = 0; i < length; i++) {
			cards[i] = i + 1;
		}

		return cards;
	}

	private void swap(int[] cards, int i, int j) {
		int temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}

	private void print(int[] cards) {
		for (int i = 0; i < cards.length; i++) {
			System.out.print(cards[i] + " ");
		}

	}

	/**
	 * 洗牌1
	 */
	public void shuffle_1st(int[] cards) {
		int length = cards.length;
		Random r = new Random();
		for (int k = 0; k < length; k++) {
			int i = r.nextInt(length);
			int j = r.nextInt(length);

			swap(cards, i, j);
		}
	}

	/**
	 * 洗牌1
	 */
	public void shuffle_2st(int[] cards) {
		int length = cards.length;
		Random r = new Random();
		for (int k = 0; k < length; k++) {
			int i = r.nextInt(length);
			swap(cards, i, k);
		}
	}

	/**
	 * 洗牌3 正确的洗牌方式
	 */
	public void shuffle_correct(int[] cards) {
		int length = cards.length;
		Random r = new Random();
		for (int k = 0; k < length; k++) {
			int rind = k + r.nextInt(length - k);
			swap(cards, rind, k);
		}
	}

	/**
	 * 洗牌测试
	 */
	@Test
	public void testShuffle() {
		// result 表示 每一列表示 1.。9数字，每一行表示在这个位置上出现的次数
		int[][] result = new int[10][10];
		for (int i = 0; i < 1000; i++) {
			int[] cards = createCards(10);
			shuffle_correct(cards);
			for (int j = 0; j < cards.length; j++) {
				result[cards[j] - 1][j] += 1;
			}
		}
		System.out.println("result");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

	}

}
