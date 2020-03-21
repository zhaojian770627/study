package com.zj.study.algorithm.recursion;

public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		System.out.println(fibonacci.one(40));
	}

	int one(int num) {
		if (num <= 2)
			return 1;

		return one(num - 1) + one(num - 1);
	}

}
