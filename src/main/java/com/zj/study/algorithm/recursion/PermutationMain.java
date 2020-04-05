package com.zj.study.algorithm.recursion;

import java.util.Arrays;

public class PermutationMain {

	public static void main(String[] args) {
//		permutation(result, new String[] { "a", "b", "c" }, 0);
//		permutationUnique("", new String[] { "2", "2", "3" });
		permutationK("", new String[] { "a", "b", "c" }, 2);
		// System.out.print("(");
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

	/**
	 * 数组方式
	 * 
	 * @param result
	 * @param input
	 */
	private static void permutationUnique(String result, String[] input) {
		if (input.length == 0) {
			System.out.println(result);
		}
		for (int i = 0; i < input.length; i++) {
			if (i != 0 && input[i].equals(input[i - 1])) {
				continue;
			}

			String[] newInput = new String[input.length - 1];
			if (i != 0)
				System.arraycopy(input, 0, newInput, 0, i);
			System.arraycopy(input, i + 1, newInput, i, input.length - i - 1);
			permutationUnique(result + input[i], newInput);
		}
	}

	/**
	 * 数组方式
	 * 
	 * @param result
	 * @param input
	 */
	private static void permutationK(String result, String[] input, int k) {
		if (k == 0) {
			System.out.println(result);
		}
		for (int i = 0; i < input.length; i++) {
			if (i != 0 && input[i].equals(input[i - 1])) {
				continue;
			}

			String[] newInput = new String[input.length - 1];
			if (i != 0)
				System.arraycopy(input, 0, newInput, 0, i);
			System.arraycopy(input, i + 1, newInput, i, input.length - i - 1);
			permutationK(result + input[i], newInput, k - 1);
		}
	}
}
