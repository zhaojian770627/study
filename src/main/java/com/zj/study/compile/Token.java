package com.zj.study.compile;

public class Token {

	String tokenText = "";
	public TokenType type;

	public void append(char ch) {
		tokenText = tokenText + ch;
	}

}
