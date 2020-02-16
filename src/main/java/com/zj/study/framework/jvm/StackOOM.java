package com.zj.study.framework.jvm;

/**
 * 演示栈溢出
 * 
 * -Xss128k
 * 
 * @author zhaojian
 *
 */
public class StackOOM {

	int level = 0;

	private void recursion(String a, String b) {
		level++;
		recursion(a, b);
	}

	public static void main(String[] args) {
		StackOOM stackOOM = new StackOOM();
		try {
			stackOOM.recursion("a", "b");
		} catch (Throwable e) {
			System.out.println("level=" + stackOOM.level);
			e.printStackTrace();
		}
	}

}
