package com.zj.study.algorithm.recursion;

public class ParenthesesMain {

	public static void main(String[] args) {
		ParenthesesMain main = new ParenthesesMain();
		main.generate("", 4, 4);
	}

	private void generate(String midres, int left, int right) {
		if (right == 0)
			System.out.println(midres);
		if (left > 0)
			generate(midres + '(', left - 1, right);
		if (right > left)
			generate(midres + ')', left, right - 1);
	}
}
