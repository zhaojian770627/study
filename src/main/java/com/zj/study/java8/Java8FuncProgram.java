package com.zj.study.java8;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class Java8FuncProgram {
	private int num = 0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {
		GreetingService greetService1 = message -> System.out.println("Hello " + message);
		greetService1.sayMessage("abcd");

		BiConsumer<String, String> bi = (T, U) -> {
			System.out.println(T + U);
		};

		bi.andThen(bi).andThen(bi).accept("a", "b");

		Comparator<String> comparator = (str1, str2) -> str1.length() - str2.length();

		System.out.println(BinaryOperator.maxBy(comparator).apply("aaaa", "bbb"));
		Object o = BinaryOperator.maxBy(comparator);
		System.out.println(o.getClass());
		new Java8FuncProgram().setValue(Java8FuncProgram::setNum);

	}

	public void setValue(BiConsumer<Java8FuncProgram, Integer> setter) {
		Java8FuncProgram program = new Java8FuncProgram();
		setter.accept(program, Integer.valueOf(10));
		System.out.println(program.getNum());

	}

}
