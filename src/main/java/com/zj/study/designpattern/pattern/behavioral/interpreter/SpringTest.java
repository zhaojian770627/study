package com.zj.study.designpattern.pattern.behavioral.interpreter;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpringTest {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("#A*2+400*1+66");
		SpelExpression spelExp = (SpelExpression) expression;
		EvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("A", 10);
		System.out.println(spelExp.getValue(ctx));
	}

}
