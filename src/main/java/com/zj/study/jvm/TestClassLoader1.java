package com.zj.study.jvm;

public class TestClassLoader1 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> clazz = Class.forName("java.lang.String");
		System.out.println(clazz.getClassLoader());

		Class<?> clazz2 = Class.forName("com.zj.study.jvm.C");
		System.out.println(clazz2.getClassLoader());
	}
}

class C {
}