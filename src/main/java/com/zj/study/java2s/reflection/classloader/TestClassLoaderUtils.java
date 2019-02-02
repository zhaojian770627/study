package com.zj.study.java2s.reflection.classloader;

public class TestClassLoaderUtils {

	public static void main(String[] args) {
		System.out.println(ClassLoaderUtils.showClassLoaderHierarchy(new TestClassLoaderUtils(), "a"));
	}
}
