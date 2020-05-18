package com.zj.study.compile;

public class TokenParse {
	Token token = new Token();

	void parse(char chars[]) {
		int length = chars.length;
		int pos = 0;
		DfaState state = DfaState.Initial;

		while (pos < length) {
			char ch = chars[pos];
			switch (state) {
			case Initial:
				state = nextState(ch);
				break;
			case Id:
				if (isAlpha(ch) || isDigit(ch)) {
					token.append(ch); // 保持标识符状态
				} else {
					state = nextState(ch); // 退出标识符状态，并保存 Token
				}
				break;
			}
			pos++;
		}
	}

	DfaState nextState(char ch) {
		DfaState newState = DfaState.Initial;
		if (isAlpha(ch)) { // 第一个字符是字母
			newState = DfaState.Id; // 进入 Id 状态
			token.type = TokenType.Identifier;
			tokenText.append(ch);
		} else if (isDigit(ch)) { // 第一个字符是数字
			newState = DfaState.IntLiteral;
			token.type = TokenType.IntLiteral;
			token.append(ch);
		} else if (ch == '>') { // 第一个字符是 >
			newState = DfaState.GT;
			token.type = TokenType.GT;
			token.append(ch);
		}
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
