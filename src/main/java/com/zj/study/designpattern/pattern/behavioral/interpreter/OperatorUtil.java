package com.zj.study.designpattern.pattern.behavioral.interpreter;

public class OperatorUtil {

	public static boolean isOperator(String symbol) {
		return (symbol.equals("+") || symbol.equals("*"));
	}

	public static Interpreter getExpressionObject(Interpreter firstExpression, Interpreter secondExpression,
			String symbol) {
		if (symbol.equals("+")) {
			return new AddInterpreter(firstExpression, secondExpression);
		} else if (symbol.equals("*")) {
			return new MultInterpreter(firstExpression, secondExpression);
		}
		return null;
	}

}
