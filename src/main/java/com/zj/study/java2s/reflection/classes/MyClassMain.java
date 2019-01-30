package com.zj.study.java2s.reflection.classes;

import java.lang.reflect.Constructor;

public class MyClassMain {
	public static void main(String args[]) throws Exception {
		Class c = Class.forName("com.zj.study.java2s.reflection.classes.MyClass");

		Constructor constructors[] = c.getDeclaredConstructors();
		Object obj = null;
		for (Constructor cons : constructors) {
			Class[] params = cons.getParameterTypes();
			if (params.length == 1 && params[0] == int.class) {
				obj = cons.newInstance(10);
				break;
			}
		}

		if (obj == null) {
			System.out.println("Can't Create MyClass object.");
			return;
		}
	}
}

class MyClass {
	private int count;

	MyClass(int c) {
		System.out.println("MyClass(int):" + c);
		count = c;
	}

	MyClass() {
		System.out.println("MyClass()");
		count = 0;
	}

	void setCount(int c) {
		System.out.println("setCount(int): " + c);
		count = c;
	}

	int getCount() {
		System.out.println("getCount():" + count);
		return count;
	}

	void showcount() {
		System.out.println("count is " + count);
	}
}