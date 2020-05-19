package com.zj.study.compile;

public class Token {

	String tokenText = "";
	public TokenType type;

	public String getTokenText() {
		return tokenText;
	}

	public void setTokenText(String tokenText) {
		this.tokenText = tokenText;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public void append(char ch) {
		tokenText = tokenText + ch;
	}

	@Override
	public String toString() {
		return type.name() + "\t" + tokenText;
	}
}
