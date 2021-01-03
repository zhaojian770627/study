package com.zj.study.jvm;

public class TestCallVirtual {

	public void test(Grandpa grandpa) {
		System.out.println("grandpa");
	}

	public void test(Father father) {
		System.out.println("father");
	}

	public void test(Son son) {
		System.out.println("son");
	}

	public static void main(String[] args) {
		Grandpa g1 = new Father();
		Grandpa g2 = new Son();

		TestCallVirtual call = new TestCallVirtual();

		// WARNING 根据静态类型确定，所以这个地方是调用的test(Grandpa grandpa)
		call.test(g1);
		call.test(g2);
	}
}

class Grandpa {
}

class Father extends Grandpa {
}

class Son extends Father {
}
