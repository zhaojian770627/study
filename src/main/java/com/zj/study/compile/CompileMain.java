package com.zj.study.compile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompileMain {

	public static void main(String[] args) throws IOException {
		File file = new File("d:\\test.zj");// 定义一个file对象，用来初始化FileReader
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		int length = (int) file.length();
		// 这里定义字符数组的时候需要多定义一个,因为词法分析器会遇到超前读取一个字符的时候，如果是最后一个
		// 字符被读取，如果在读取下一个字符就会出现越界的异常
		char buf[] = new char[length + 1];
		reader.read(buf);
		reader.close();
		System.out.println("end!");
	}
}
