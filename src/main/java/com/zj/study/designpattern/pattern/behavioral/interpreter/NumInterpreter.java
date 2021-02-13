package com.zj.study.designpattern.pattern.behavioral.interpreter;

public class NumInterpreter implements Interpreter {
	private int number;

	public NumInterpreter(int number) {
		this.number = number;
	}

	public NumInterpreter(String number) {
		this.number = Integer.parseInt(number);
	}

	@Override
	public int interpret() {
		return this.number;
	}

	@Override
	public String toString() {
		return number + "";
	}

}
