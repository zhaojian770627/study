package com.zj.study.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CombSumMain {

	public static void main(String[] args) {
		CombSumMain main = new CombSumMain();
		// main.subsets(3);
		main.combSum(new int[] { 2, 3, 6, 7 }, 20);
	}

	private void combSum(int[] ary, int sum) {
		Stack<Integer> stack = new Stack<>();
		List<Integer[]> result = new ArrayList<>();
		subsets_recursive_helper(result, stack, ary, sum, 0);

		System.out.print("[");
		for (Integer[] ss : result) {
			System.out.print("(");
			for (int s : ss) {
				System.out.print(s + " ");
			}
			System.out.print(") ");
		}
		System.out.print("]");
	}

	// [() (a ) (a b ) (a b c ) (a c ) (b ) (b c ) (c ) ]
	// 每次从递归中退出,函数全部pop,而上一层还停留指定的位置
	private void subsets_recursive_helper(List<Integer[]> result, Stack<Integer> queue, int[] ary, int remains, int i) {
		if (remains < 0)
			return;
		if (remains == 0) {
			result.add(queue.toArray(new Integer[] {}));
		} else
			for (int j = i; j < ary.length; j++) {
				queue.push(ary[j]);
				subsets_recursive_helper(result, queue, ary, remains - ary[j], j);
				queue.pop();
			}
	}

}
