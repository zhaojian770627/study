package com.zj.study.compile;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TokensReader {
	Token token = new Token();

	static Set<String> keyWords = new HashSet<>();
	static {
		keyWords.add("int");
	}

	// 读取位置
	int pos = 0;
	char[] chars;
	Stack<Token> tokenBuf = new Stack<>();

	public void setChars(char[] chars) {
		this.chars = chars;
	}

	Token getToken() {
		if (tokenBuf.size() > 0)
			return tokenBuf.pop();
		else {
			getTokenToBuf();
			if (tokenBuf.size() > 0)
				return tokenBuf.pop();
			else
				return null;
		}
	}

	void getTokenToBuf() {
		int length = chars.length;
		DfaState state = DfaState.Initial;

		while (pos < length) {
			char ch = chars[pos];
			if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
				if (token != null) {
					if (token.getType().equals(TokenType.Identifier) && keyWords.contains(token.getTokenText()))
						token.setType(TokenType.KeyWord);

					tokenBuf.push(token);
					state = DfaState.Initial;
					return;
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
					if (keyWords.contains(token.getTokenText()))
						token.setType(TokenType.KeyWord);

					tokenBuf.push(token);
					return;
				}
				break;
			case GT:
				if (ch == '=') {
					token.type = TokenType.GE; // 转换成 GE
					state = DfaState.GE;
					token.append(ch);
				} else {
					tokenBuf.push(token);
					return;
				}
				break;
			case GE:
				tokenBuf.push(token);
				return;
			case EQ:
				tokenBuf.push(token);
				return;
			case IntLiteral:
				if (isDigit(ch)) {
					token.append(ch); // 继续保持在数字字面量状态
				} else {
					tokenBuf.push(token);
					return;
				}
				break;
			case UNKNOWN:
				tokenBuf.push(token);
				return;
			default:
				System.err.println("此分支不应该到达" + ch);
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
		} else if (ch == '=') {
			newState = DfaState.EQ;
			token.type = TokenType.EQ;
			token.append(ch);
		} else {
			newState = DfaState.UNKNOWN;
			token.type = TokenType.UNKNOWN;
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
}
