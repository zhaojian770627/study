package com.zj.study.algorithm.recursion;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ParenthesesMain {

	public static void main(String[] args) {
		ParenthesesMain main = new ParenthesesMain();
		// main.generate("", 4, 4);
		Stack<String> stack = new Stack<>();
		main.generate2(stack, 4, 4);
	}

	private void generate(String midres, int left, int right) {
		if (right == 0)
			System.out.println(midres);
		if (left > 0)
			generate(midres + '(', left - 1, right);
		if (right > left)
			generate(midres + ')', left, right - 1);
	}

	private void generate2(Stack<String> stack, int left, int right) {
		if (right == 0) {
			for (String s : stack) {
				System.out.print(s);
			}
			System.out.println();
		}
		if (left > 0) {
			stack.push("(");
			generate2(stack, left - 1, right);
			stack.pop();
		}
		if (right > left) {
			stack.push(")");
			generate2(stack, left, right - 1);
			stack.pop();
		}
	}
}
