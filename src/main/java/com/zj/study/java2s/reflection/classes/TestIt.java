package com.zj.study.java2s.reflection.classes;

public class TestIt {
	public static void main(String a[]) {
		new TestIt().doit();
	}

	public void doit() {
		new InnerClass().sayHello();
	}

	public void enclosingClassMethod() {
		System.out.println("Hello world!");
	}

	class InnerClass {
		public void sayHello() {
			TestIt.this.enclosingClassMethod();
		}
	}
}