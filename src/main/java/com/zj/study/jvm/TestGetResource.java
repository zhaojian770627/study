package com.zj.study.jvm;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class TestGetResource {

	public static void main(String[] args) throws IOException {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		String resourceName = "com/zj/study/jvm/MyClassLoader.class";
		Enumeration<URL> urls = contextClassLoader.getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			System.out.println(url);
		}
	}

}
