package com.zj.study.algorithm.recursion;

import java.util.LinkedList;
import java.util.List;

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

}
