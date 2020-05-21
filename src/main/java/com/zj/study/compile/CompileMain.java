package com.zj.study.compile;

import java.io.IOException;

public class CompileMain {

	public static void main(String[] args) throws IOException {
		TokenReader tokensReader = new TokenReader("d:\\test.zj");
		tokensReader.initReader();
		Token token = tokensReader.pop();
		while (token != null) {
			System.out.println(token);
			token = tokensReader.pop();
		}

		System.out.println("end!");
	}
}
