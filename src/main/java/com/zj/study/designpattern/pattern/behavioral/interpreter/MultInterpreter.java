package com.zj.study.designpattern.pattern.behavioral.interpreter;

public class MultInterpreter implements Interpreter {
	private Interpreter firstExpression, secondExpression;

	public MultInterpreter(Interpreter firstExpression, Interpreter secondExpression) {
		this.firstExpression = firstExpression;
		this.secondExpression = secondExpression;
	}

	@Override
	public int interpret() {
		return this.firstExpression.interpret() * this.secondExpression.interpret();
	}

	@Override
	public String toString() {
		return "*";
	}
}
