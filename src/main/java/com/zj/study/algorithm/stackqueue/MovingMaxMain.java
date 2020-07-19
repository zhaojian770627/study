package com.zj.study.algorithm.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 滑动窗口取最大值
 * 
 */
public class MovingMaxMain {

	public static void main(String[] args) {
		MovingMaxMain main = new MovingMaxMain();

		int[] ary = { 12, 1, 78, 90, 57, 89, 56 };

		main.movingMax(ary, 3);

//		main.testDeque();
	}

	private void testDeque() {
		Stack<Integer> qi = new Stack<>();
		qi.push(0);
		qi.push(1);

//		qi.pop();

		System.out.println(qi.firstElement());
	}

	private void movingMax(int[] ary, int k) {
		int n = ary.length;

		Stack<Integer> qi = new Stack<>();

		for (int i = 0; i < k; i++) {
			while (qi.size() > 0 && ary[i] >= ary[qi.peek()])
				qi.pop();

			qi.push(i);
		}

		for (int i = k; i < n; i++) {
			System.out.println(ary[qi.firstElement()]);

			while (qi.size() > 0 && qi.get(0) <= i - k)
				qi.remove(0);

			while (qi.size() > 0 && ary[i] >= ary[qi.peek()])
				qi.pop();

			qi.push(i);
		}

		System.out.println(ary[qi.firstElement()]);
	}
}
