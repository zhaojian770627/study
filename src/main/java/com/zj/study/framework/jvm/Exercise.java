package com.zj.study.framework.jvm;

public class Exercise {

	public static void main(String[] args) {
		Exercise e1 = new Exercise();
		Exercise e2 = new Exercise();
		print(e1, e2);
	}

	private static void print(Exercise e1, Exercise e2) {
		System.out.println(e1 == (e1 = e2));
	}

}
