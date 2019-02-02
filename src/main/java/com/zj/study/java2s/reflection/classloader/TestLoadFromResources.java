package com.zj.study.java2s.reflection.classloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestLoadFromResources {

	public static void main(String[] args) throws IOException {
		File file = new File("/home/zj/mlj.jar");
		URL url = file.toURI().toURL();
		JarClassLoader loader = new JarClassLoader(url);
		URL[] urls = loader.getURLs();
		for (URL u : urls)
			System.out.println(u.getPath());
		Resources.setDefaultClassLoader(loader);
		InputStream in = Resources.getResourceAsStream("Koala.jpg");
		FileOutputStream out = new FileOutputStream("/home/zj/Koala.jpg");
		byte[] bs = new byte[1024];
		int len;
		while ((len = in.read(bs)) != -1) {
			out.write(bs, 0, len);
		}
		in.close();
		out.close();
	}

}
