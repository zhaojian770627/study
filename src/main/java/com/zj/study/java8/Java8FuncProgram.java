package com.zj.study.java8;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class Java8FuncProgram {

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

	}

}
