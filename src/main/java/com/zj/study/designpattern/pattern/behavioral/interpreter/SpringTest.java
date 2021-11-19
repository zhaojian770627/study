package com.zj.study.designpattern.pattern.behavioral.interpreter;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringTest {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("#A*2+400*1+66");
		SpelExpression spelExp = (SpelExpression) expression;
		spelExp.setValue("A", 10);
		int result = (int) expression.getValue();
		System.out.println(result);
	}

}
