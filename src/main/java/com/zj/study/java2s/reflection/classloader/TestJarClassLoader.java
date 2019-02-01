package com.zj.study.java2s.reflection.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

public class TestJarClassLoader {

	public static void main(String[] args) throws Exception {
		File file = new File("/home/zj/mlj.jar");
		URL url = file.toURI().toURL();
		JarClassLoader loader = new JarClassLoader(url);
		Class c = loader.loadClass("ai.Eight.Main");
		Method m = c.getMethod("main", String[].class);
		Object o = c.newInstance();
		m.invoke(o, (Object) new String[] {});
	}
}
