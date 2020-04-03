package com.zj.study.algorithm.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SubsetsMain {

	public static void main(String[] args) {
		SubsetsMain main = new SubsetsMain();
		// main.subsets(3);
		main.subsets2(new String[] { "a", "b", "c" });
	}

	private void subsets(int n) {
		List<List<Integer>> result = new LinkedList<>();
		result.add(new LinkedList<>());
		for (int i = 1; i <= n; i++) {
			List<List<Integer>> newResult = new LinkedList<>();
			newResult.addAll(result);
			for (List<Integer> e : result) {
				List<Integer> l = new LinkedList<>();
				l.addAll(e);
				l.add(i);
				newResult.add(l);
			}
			result = newResult;
		}
		System.out.print("(");
		for (List<Integer> le : result) {
			System.out.print("(");
			for (Integer i : le) {
				System.out.print(i + " ");
			}
			System.out.print(") ");
		}
		System.out.print(")");
	}

	private void subsets2(String[] ary) {
		Stack<String> stack = new Stack<>();
		List<String[]> result = new ArrayList<>();
		subsets_recursive_helper(result, stack, ary, 0);

		System.out.print("[");
		for (String[] ss : result) {
			System.out.print("(");
			for (String s : ss) {
				System.out.print(s + " ");
			}
			System.out.print(") ");
		}
		System.out.print("]");
	}

	// [() (a ) (a b ) (a b c ) (a c ) (b ) (b c ) (c ) ]
	// 每次从递归中退出,函数全部pop,而上一层还停留指定的位置
	private void subsets_recursive_helper(List<String[]> result, Stack<String> queue, String[] ary, int i) {
		result.add(queue.toArray(new String[] {}));
		for (int j = i; j < ary.length; j++) {
			queue.push(ary[j]);
			subsets_recursive_helper(result, queue, ary, j + 1);
			queue.pop();
		}
	}

}
