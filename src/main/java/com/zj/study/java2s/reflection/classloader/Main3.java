package com.zj.study.java2s.reflection.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

class MyClass {
	public String myMethod() {
		return "a message";
	}
}

public class Main3 {
	public static void main(String[] argv) throws Exception {
		URL[] urls = null;
		File dir = new File(System.getProperty("user.dir") + File.separator + "dir" + File.separator);
		URL url = dir.toURI().toURL();
		urls = new URL[] { url };
		ClassLoader cl = new URLClassLoader(urls);
		Class cls = cl.loadClass("MyClass");
		MyClass myObj = (MyClass) cls.newInstance();
	}
}