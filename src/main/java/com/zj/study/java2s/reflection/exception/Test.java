package com.zj.study.java2s.reflection.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import com.zj.study.java2s.reflection.classpath.ClassPath;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method m = ClassPath.class.getMethod("getInputStream", String.class);
		System.out.println(ReflectionUtils.declaresException(m, IOException.class));
	}

}
