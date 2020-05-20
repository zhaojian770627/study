package com.zj.study.compile.Calculator;

import com.zj.study.compile.Token;
import com.zj.study.compile.TokenReader;
import com.zj.study.compile.TokenType;

public class SimpleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private TokenReader tokenReader;

	public SimpleCalculator(TokenReader tokenReader) {
		this.tokenReader = tokenReader;
	}

	public void intDeclare() {
		SimpleASTNode node = null;
		Token token = tokenReader.peek(); // 预读

		if (token != null && token.getType() == TokenType.INT) { // 匹配 Int
			token = tokenReader.pop();
		}
	}

}
