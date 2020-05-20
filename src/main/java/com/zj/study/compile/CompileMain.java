package com.zj.study.compile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompileMain {

	public static void main(String[] args) throws IOException {
		File file = new File("d:\\test.zj");// 定义一个file对象，用来初始化FileReader
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		int length = (int) file.length();
		char buf[] = new char[length + 1];
		reader.read(buf);
		buf[length] = ' ';
		reader.close();

		TokensReader tokensReader = new TokensReader();
		tokensReader

		System.out.println("end!");
	}
}
