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
	public void shuffle_lst(int[] cards) {
		int length = cards.length;
		Random r = new Random();
		for (int k = 0; k < length; k++) {
			int i = r.nextInt(length);
			int j = r.nextInt(length);

			swap(cards, i, j);
		}
	}

	@Test
	/**
	 * 洗牌测试
	 */
	public void testShuffle() {
		int[] cards = createCards(10);
		shuffle_lst(cards);
		print(cards);
	}

}
