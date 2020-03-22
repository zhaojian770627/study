package com.zj.study.algorithm.recursion;

public class Intseq {

	public static void main(String[] args) {
		Intseq intseq = new Intseq();
		System.out.println(intseq.one(5, 101));
	}

	/**
	 * one(5,101)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	String one(int a, int b) {
		if (a == b)
			return "" + a;

		if (b % 2 == 1) {
			return "(" + one(a, b - 1) + "+1)";
		}

		// 偶数
		if (b < 2 * a) {
			return "(" + one(a, b - 1) + "+1)";
		}

		return one(a, b / 2) + "*2";
	}

}
