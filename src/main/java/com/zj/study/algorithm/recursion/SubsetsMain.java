package com.zj.study.algorithm.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SubsetsMain {

	public static void main(String[] args) {
		SubsetsMain main = new SubsetsMain();
		main.subsets(3);
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
		Queue<String> queue = new LinkedList<String>();
		List<String> result = new ArrayList<>();
		subsets_recursive_helper(result, queue, ary, 0);
	}

	private void subsets_recursive_helper(List<String> result, Queue<String> queue, String[] ary, int i) {
		result.addAll(queue);
//		queue.add(e)
//		queue.poll();
	}

}
