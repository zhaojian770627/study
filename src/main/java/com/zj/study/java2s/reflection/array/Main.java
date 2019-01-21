package com.zj.study.java2s.reflection.array;

import java.lang.reflect.Array;

public class Main {
	public static void main(String[] argv) throws Exception {
		// Creating an Array: An array of 10 ints.
		int[] ints = (int[]) Array.newInstance(int.class, 10);
		// Creating an Array: An array of 10 int-arrays.
		int[][] ints2 = (int[][]) Array.newInstance(int[].class, 10);
		// Creating an Array: A 10x20 2-dimensional int array.
		int[][] ints2a = (int[][]) Array.newInstance(int.class, new int[] { 10, 20 });

		// Getting and Setting the Value of an Element in an Array Object
		int[] array = { 1, 2, 3 };
		// Get the value of the third element.
		Object o = Array.get(array, 2);

		// Set the value of the third element.
		Array.set(array, 2, 1);

	}

}