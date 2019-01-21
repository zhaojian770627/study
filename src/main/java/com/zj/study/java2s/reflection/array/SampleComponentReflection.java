package com.zj.study.java2s.reflection.array;

import java.awt.Button;

public class SampleComponentReflection {

	public static void main(String[] args) {
		int[] ints = new int[2];
		Button[] buttons = new Button[6];
		String[][] twoDim = new String[4][5];

		printComponentType(ints);
		printComponentType(buttons);
		printComponentType(twoDim);
	}

	static void printComponentType(Object array) {
		Class arrayClass = array.getClass();
		String arrayName = arrayClass.getName();
		Class componentClass = arrayClass.getComponentType();
		String componentName = componentClass.getName();
		System.out.println("Array: " + arrayName + ", Component: " + componentName);
	}
}