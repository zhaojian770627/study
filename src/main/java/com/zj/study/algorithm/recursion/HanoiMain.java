package com.zj.study.algorithm.recursion;

public class HanoiMain {

	public static void main(String[] args) {
		HanoiMain hanoiMain = new HanoiMain();
		hanoiMain.hanoi(3, "L", "M", "R");
	}

	void hanoi(int n, String L, String M, String R) {
		if (n == 1)
			System.out.println("Move from " + L + " to " + R);
		else {
			hanoi(n - 1, L, R, M);
			hanoi(1, L, M, R);
			hanoi(n - 1, M, L, R);
		}
	}
}
