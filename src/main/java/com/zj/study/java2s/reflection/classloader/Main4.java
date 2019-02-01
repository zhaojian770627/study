package com.zj.study.java2s.reflection.classloader;

public class Main4 {
	public static void main(String[] args) {
		Main4 csl = new Main4();
		csl.getCodeSourceLocation();
	}

	private void getCodeSourceLocation() {
		System.out.println("Code source location: " + getClass().getProtectionDomain().getCodeSource().getLocation());
	}
}