package com.zj.study.java2s.reflection.array;

import java.lang.reflect.Array;

/**
 * Array Reflection: Multi Array Reflection
 * 
 *
 */
public class SampleMultiArrayReflection {

	public static void main(String[] args) {

		// The oneDimA and oneDimB objects are one dimensional int arrays
		// with 5 elements.

		int[] dim1 = { 5 };
		int[] oneDimA = (int[]) Array.newInstance(int.class, dim1);
		int[] oneDimB = (int[]) Array.newInstance(int.class, 5);

		// The twoDimStr object is a 5 X 10 array of String objects.
		int[] dimStr = { 5, 10 };
		String[][] twoDimStr = (String[][]) Array.newInstance(String.class, dimStr);

		// The twoDimA object is an array of 12 int arrays. The tail
		// dimension is not defined. It is equivalent to the array
		// created as follows:
		// int[][] ints = new int[12][];
		int[] dimA = { 12 };
		int[][] twoDimA = (int[][]) Array.newInstance(int[].class, dimA);
	}
}