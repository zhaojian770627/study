package com.zj.study.algorithm.recursion;

/**
 * 
 * 格雷码
 * 
 * @author zhaojian
 *
 */
public class GrayCode {

	public static void main(String[] args) {
		GrayCode grayCode = new GrayCode();
		grayCode.moves_ins(4, true);
	}

	void moves_ins(int n, boolean forward) {
		if (n == 0)
			return;
		moves_ins(n - 1, true);
		if (forward)
			System.out.println("enter " + n);
		else
			System.out.println("exit  " + n);
		moves_ins(n - 1, false);
	}
}