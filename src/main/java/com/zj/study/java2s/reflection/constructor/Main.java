package com.zj.study.java2s.reflection.constructor;

import java.lang.reflect.Constructor;

public class Main {
	public static void main(String[] argv) throws Exception {
		Constructor con = java.awt.Point.class.getConstructor(new Class[] { int.class, int.class });
		java.awt.Point obj = (java.awt.Point) con.newInstance(new Object[] { new Integer(123), new Integer(123) });
	}
}