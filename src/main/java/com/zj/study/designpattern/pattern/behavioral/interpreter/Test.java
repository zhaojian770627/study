package com.zj.study.designpattern.pattern.behavioral.interpreter;

public class Test {

	public static void main(String[] args) {
		String geelyInputStr = "6 100 11 + *";
		GeelyExpressionParser expressionParse = new GeelyExpressionParser();
		int result = expressionParse.parse(geelyInputStr);
		System.out.println("解释器计算结果:" + result);
	}

}
