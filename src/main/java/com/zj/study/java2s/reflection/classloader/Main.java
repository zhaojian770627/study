package com.zj.study.java2s.reflection.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
	public static void main(String[] argv) throws Exception {
		File file = new File("/home/zj/git/javastudy/target/classes/com/zj/study/java2s/reflection/classloader");
		URL url = file.toURI().toURL();
		URL[] urls = new URL[] { url };
		ClassLoader cl = new URLClassLoader(urls);
		Class cls = cl.loadClass("com.zj.study.java2s.reflection.classloader.Main1");
		cls.newInstance();
	}
}
