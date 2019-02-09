package com.zj.study.java2s.reflection.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main5 {
	public static void main(String[] argv) throws Exception {

		Class cls = java.lang.String.class;
		Method method = cls.getMethods()[0];
		Field field = cls.getFields()[0];
		Constructor constructor = cls.getConstructors()[0];
		String name;

		name = cls.getName().substring(cls.getPackage().getName().length() + 1);
		System.out.println(name);
		name = field.getName();
		System.out.println(name);
		name = constructor.getName().substring(cls.getPackage().getName().length() + 1);
		System.out.println(name);
		name = method.getName();
		System.out.println(name);

	}
}