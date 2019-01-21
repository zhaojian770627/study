package com.zj.study.java2s.reflection.array;

import java.lang.reflect.Array;

public class SampleCreateArrayReflection {

	public static void main(String[] args) {
		int[] originalArray = { 55, 66 };
		int[] biggerArray = (int[]) doubleArray(originalArray);
		System.out.println("originalArray:");
		for (int k = 0; k < Array.getLength(originalArray); k++)
			System.out.println(originalArray[k]);
		System.out.println("biggerArray:");
		for (int k = 0; k < Array.getLength(biggerArray); k++)
			System.out.println(biggerArray[k]);
	}

	static Object doubleArray(Object source) {
		int sourceLength = Array.getLength(source);
		Class arrayClass = source.getClass();
		Class componentClass = arrayClass.getComponentType();
		Object result = Array.newInstance(componentClass, sourceLength * 2);
		System.arraycopy(source, 0, result, 0, sourceLength);
		return result;
	}
}