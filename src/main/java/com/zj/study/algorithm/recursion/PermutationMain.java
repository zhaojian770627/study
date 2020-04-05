package com.zj.study.algorithm.recursion;

public class PermutationMain {

	public static void main(String[] args) {
		permutationUnique("", "223");
//		permutation(result, new String[] { "a", "b", "c" }, 0);
//		System.out.print("(");
//		for (String[] str : result) {
//			System.out.print("(");
//			for (String s : str) {
//				System.out.print(s + " ");
//			}
//			System.out.print(") ");
//		}
//		System.out.print(")");
	}

	private static void permutation(String result, String input) {
		if (input.length() == 0)
			System.out.println(result);
		for (int i = 0; i < input.length(); i++) {
			permutation(result + input.substring(i, i + 1), input.substring(0, i) + input.substring(i + 1));
		}
	}

	private static void permutationUnique(String result, String input) {
		if (input.length() == 0) {
			System.out.println(result);
		}
		for (int i = 0; i < input.length(); i++) {
			if (i != 0 && input.substring(i, i + 1).equals(input.substring(i - 1, i))) {
				continue;
			}
			permutationUnique(result + input.substring(i, i + 1), input.substring(0, i) + input.substring(i + 1));
		}
	}
}
