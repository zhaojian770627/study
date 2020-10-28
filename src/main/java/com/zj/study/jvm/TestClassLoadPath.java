package com.zj.study.jvm;

public class TestClassLoadPath {

	public static void main(String[] args) {
		System.out.println(System.getProperty("sun.boot.class.path"));// 启动类加载器加载路径
		System.out.println(System.getProperty("java.ext.dirs"));// 扩展类加载器加载路径
		System.out.println(System.getProperty("java.class.path"));// 应用类加载器加载路径
	}

}
