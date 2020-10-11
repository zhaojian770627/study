package com.zj.study.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
	private String path;
	private String classLoaderName;
	private final String fileExtension = ".class";

	public MyClassLoader(String classLoaderName, String basePath) {
		super();
		this.classLoaderName = classLoaderName;
		this.path = basePath;
	}

	public MyClassLoader(ClassLoader parent, String classLoaderName, String basePath) {
		super(parent);
		this.classLoaderName = classLoaderName;
		this.path = basePath;
	}

	@Override
	public String toString() {
		return "MyClassLoader [classLoaderName=" + classLoaderName + "]";
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		System.out.println(classLoaderName);
		byte[] data = this.loadClassData(className);
		return this.defineClass(className, data, 0, data.length);
	}

	private byte[] loadClassData(String className) {
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			is = new FileInputStream(new File(path));
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

	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\zj\\study\\jvm\\TestClass.class";

		MyClassLoader loader1 = new MyClassLoader("loader1", path);
		Class<?> clazzA = loader1.loadClass("com.zj.study.jvm.TestClass");
		System.out.println(clazzA.getClassLoader() + " " + clazzA.hashCode());
		Object objectA = clazzA.newInstance();
		System.out.println(objectA);

		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2", path);
		Class<?> clazzB = loader2.loadClass("com.zj.study.jvm.TestClass");
		System.out.println(clazzB.getClassLoader() + " " + clazzB.hashCode());
		Object objectB = clazzB.newInstance();
		System.out.println(objectB);
	}

}
