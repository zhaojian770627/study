package com.zj.study.jvm;

public class TestClassLoader2 {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyClassLoader myClassLoader = new MyClassLoader("myclassloader", "D:\\tmp\\");
		Class<?> clazz = myClassLoader.loadClass("com.zj.study.jvm.MySample");
		System.out.println("class:" + clazz.hashCode());
		System.out.println("classloader:" + clazz.getClassLoader());

		Object o = clazz.newInstance();
	}

}
