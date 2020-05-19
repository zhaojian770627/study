package com.zj.study.compile;

import java.util.ArrayList;
import java.util.List;

public class TokenParse {
	Token token = new Token();
	List<Token> tokens = new ArrayList<>();

	public List<Token> getTokens() {
		return tokens;
	}

	void parse(char chars[]) {
		int length = chars.length;
		int pos = 0;
		DfaState state = DfaState.Initial;

		while (pos < length) {
			char ch = chars[pos];
			if (ch == ' ' || ch == '\t') {
				if (token != null) {
					tokens.add(token);
					token = null;
					state = DfaState.Initial;
				}
				pos++;
				continue;
			}

			switch (state) {
			case Initial:
				state = nextState(ch);
				break;
			case Id:
				if (isAlpha(ch) || isDigit(ch)) {
					token.append(ch); // 保持标识符状态
				} else {
					tokens.add(token);
					state = nextState(ch); // 退出标识符状态，并保存 Token
				}
				break;
			case GT:
				if (ch == '=') {
					token.type = TokenType.GE; // 转换成 GE
					state = DfaState.GE;
					token.append(ch);
				} else {
					tokens.add(token);
					state = nextState(ch); // 退出 GT 状态，并保存 Token
				}
				break;
			case GE:
				tokens.add(token);
				state = nextState(ch); // 退出当前状态，并保存 Token
				break;
			case IntLiteral:
				if (isDigit(ch)) {
					token.append(ch); // 继续保持在数字字面量状态
				} else {
					tokens.add(token);
					state = nextState(ch); // 退出当前状态，并保存 Token
				}
				break;
			}
			pos++;
		}
	}

	// 决定下一个状态
	DfaState nextState(char ch) {
		token = new Token();
		DfaState newState = DfaState.Initial;
		if (isAlpha(ch)) { // 第一个字符是字母
			newState = DfaState.Id; // 进入 Id 状态
			token.type = TokenType.Identifier;
			token.append(ch);
		} else if (isDigit(ch)) { // 第一个字符是数字
			newState = DfaState.IntLiteral;
			token.type = TokenType.IntLiteral;
			token.append(ch);
		} else if (ch == '>') { // 第一个字符是 >
			newState = DfaState.GT;
			token.type = TokenType.GT;
			token.append(ch);
		}
		return newState;
	}

	// 判断是否是数字
	boolean isDigit(char digit) {
		if (digit >= '0' && digit <= '9')
			return true;
		else
			return false;
	}

	// 判断是否是字母
	boolean isAlpha(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
			return true;
		else
			return false;
	}

	public void outTokens() {
		for (Token token : tokens) {
			System.out.println(token);
		}
	}
}
