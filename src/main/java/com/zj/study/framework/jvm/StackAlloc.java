package com.zj.study.framework.jvm;

/**
 * 
 * 演示栈上分配
 * 
 * 虚拟机参数:-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations -XX:-UseTLAB
 * 
 * -Xmx10m和-Xms10m：堆的大小
 *	-XX:+DoEscapeAnalysis：启用逃逸分析(默认打开)
 *	-XX:+PrintGC：打印GC日志
 *	-XX:+EliminateAllocations：标量替换(默认打开)
 *	-XX:-UseTLAB 关闭本地线程分配缓冲
 *
 *	TLAB： ThreadLocalAllocBuffer
 * @author zhaojian
 *
 */
public class StackAlloc {

	public static class User {
		public int id = 0;
		public String name = "";
	}

	public static void alloc() {
		User u = new User();
		u.id = 1;
		u.name = "zj";
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			alloc();
		}

		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

}
