package com.zj.study.java2s.reflection.array;

import java.lang.reflect.Array;

public class SampleGetArrayReflection {

	public static void main(String[] args) {
		int[] sourceInts = { 12, 78 };
		int[] destInts = new int[2];
		copyArray(sourceInts, destInts);
		String[] sourceStrgs = { "Hello ", "there ", "everybody" };
		String[] destStrgs = new String[3];
		copyArray(sourceStrgs, destStrgs);
	}

	public static void copyArray(Object source, Object dest) {
		for (int i = 0; i < Array.getLength(source); i++) {
			Array.set(dest, i, Array.get(source, i));
			System.out.println(Array.get(dest, i));
		}
	}
}