package com.zj.study.algorithm.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口取最大值
 * 
 */
public class MovingMaxMain {

	public static void main(String[] args) {
		MovingMaxMain main = new MovingMaxMain();

		int[] ary = { 12, 1, 78, 90, 57, 89, 56 };

		main.movingMax(ary, 3);
	}

	private void movingMax(int[] ary, int k) {
		int n = ary.length;
		Deque<Integer> qi = new ArrayDeque<>();

		for (int i = 0; i < k; i++) {
			while (qi.size() > 0 && ary[i] >= ary[qi.peek()])
				qi.pop();

			qi.add(i);
		}

		for (int i = k; i < n; i++) {
			System.out.println(ary[qi.peek()]);

			while (qi.size() > 0 && qi.peek() <= i - k)
				qi.pop();

			while (qi.size() > 0 && ary[i] >= ary[qi.peek()])
				qi.pop();

			qi.add(i);
		}

		System.out.println(ary[qi.peek()]);
	}
}
