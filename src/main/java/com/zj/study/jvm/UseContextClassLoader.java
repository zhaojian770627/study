package com.zj.study.jvm;

/*
 * 线程上下文加载器的一般使用模式(获取-使用-还原)
 * 
 * ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
 * try{
 * 	Thread.currentThread().setContextClassLoader(targetTccl);
 * 	myMethod();
 * }finally{
 * 	Thread.currentThread().setContextClassLoader(classLoader);
 * }
 * 
 * myThread里面调用了Thread.currentThread().getContextClassLoader(),获取当前线程的上下文类加载器中做某些事情。
 * 如果一个类由类加载器A加载，那么这个类的依赖类也是由相同的类加载器加载的(如果该依赖类没有被加载过)
 * ContextClassLoader的作用就是为了破坏Java的类加载委托机制。
 * 
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（或实例化）底层的类时，就必须要通过线程上下文类加载器器来帮助高层的ClassLoader
 * 找到并加载该类。
 * 
 */
public class UseContextClassLoader {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(Thread.class.getClassLoader());
		
		System.out.println(UseContextClassLoader.class.getClassLoader());
	}

}
