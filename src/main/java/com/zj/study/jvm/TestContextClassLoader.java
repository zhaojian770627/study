package com.zj.study.jvm;

public class TestContextClassLoader implements Runnable {

	private Thread thread;

	public TestContextClassLoader() {
		thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		new TestContextClassLoader();
	}

	@Override
	public void run() {
		ClassLoader classLoader = thread.getContextClassLoader();
		thread.setContextClassLoader(classLoader);

		System.out.println("ContextClassLoader class:" + classLoader.getClass());
		System.out.println("ContextClassLoader parent class:" + classLoader.getParent().getClass());
	}
}
