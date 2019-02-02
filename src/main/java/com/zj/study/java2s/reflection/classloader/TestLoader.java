package com.zj.study.java2s.reflection.classloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

public class TestLoader {

	public static void main(String[] args) throws Throwable {
		File file = new File("/home/zj/mlj.jar");
		URL url = file.toURI().toURL();
		JarClassLoader loader = new JarClassLoader(url);
		Thread.currentThread().setContextClassLoader(loader);
		// ClassUtils util=new ClassUtils();
		// Class c = ClassUtils.forName("ai.Eight.Main");
		Class c = Class.forName("ai.Eight.Main", false, loader);
		Method m = c.getMethod("main", String[].class);
		// m.invoke(null, (Object) new String[] {});
		ClassUtilsA.invokeStaticMethod(c, "main", (Object) new String[] {}, String[].class);
	}

}
