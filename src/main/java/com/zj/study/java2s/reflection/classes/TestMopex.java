package com.zj.study.java2s.reflection.classes;

import java.lang.reflect.Field;

public class TestMopex {

	public int i;

	public static void main(String[] args) {
		Field[] f = Mopex.getInstanceVariables(TestMopex.class);
		System.out.println(f.length);
	}
}