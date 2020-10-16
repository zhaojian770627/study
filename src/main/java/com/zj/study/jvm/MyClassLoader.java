package com.zj.study.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
	private String basePath;
	private String classLoaderName;
	private final String fileExtension = ".class";

	public MyClassLoader(String classLoaderName, String basePath) {
		super();
		this.classLoaderName = classLoaderName;
		this.basePath = basePath;
	}

	public MyClassLoader(ClassLoader parent, String classLoaderName, String basePath) {
		super(parent);
		this.classLoaderName = classLoaderName;
		this.basePath = basePath;
	}

	@Override
	public String toString() {
		return "MyClassLoader [classLoaderName=" + classLoaderName + "]";
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		System.err.println("findClass executed!");
		byte[] data = this.loadClassData(className);
		return this.defineClass(className, data, 0, data.length);
	}

	private byte[] loadClassData(String className) {
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		className = className.replace(".", "/");
		try {
			is = new FileInputStream(new File(basePath + className + fileExtension));
			int ch = 0;
			while (-1 != (ch = is.read())) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return data;
	}

	public static void test(ClassLoader classLoader) throws Exception {
		Class<?> clazz = classLoader.loadClass("com.zj.study.jvm.TestClass");
		Object object = clazz.newInstance();
		System.out.println(object);
	}

	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\";
		MyClassLoader loader1 = new MyClassLoader("loader1", path);
		test(loader1);
	}

}
