package com.zj.study.java2s.reflection.constructor;

public class Main {
	public static void main(String args[]) throws Exception {
		String name = "java.lang.String";
		String methodName = "toLowerCase";

		Class cl = Class.forName(name);
		java.lang.reflect.Constructor constructor = cl.getConstructor(new Class[] { String.class });
		Object invoker = constructor.newInstance(new Object[] { "AAA" });
		Class arguments[] = new Class[] {};
		java.lang.reflect.Method objMethod = cl.getMethod(methodName, arguments);
		Object result = objMethod.invoke(invoker, (Object[]) arguments);
		System.out.println(result);
	}
}