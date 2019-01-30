package com.zj.study.java2s.reflection.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TestClassFile {

	public static void main(String[] args) throws Exception {
		File inFile = new File("/home/zj/Simple.class");
		InputStream in = null;
		in = new FileInputStream(inFile);
		ClassFile cf = new ClassFile();
		cf.read(in);
		cf.display(System.out);
	}

}
