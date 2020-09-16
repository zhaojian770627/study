package com.zj.study.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8StreamTester {

	public static void main(String[] args) {
		System.out.println("使用 Java 7: ");

		// 计算空字符串
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		System.out.println("列表: " + strings);

		long count = getCountEmptyStringUsingJava7(strings);
		System.out.println("字符串长度为 3 的数量为: " + count);

		// 删除空字符串
		List<String> filtered = deleteEmptyStringsUsingJava7(strings);
		System.out.println("筛选后的列表: " + filtered);

		System.out.println("使用 Java 8: ");
		System.out.println("列表: " + strings);
		count = strings.stream().filter(s -> s.isEmpty()).count();
		System.out.println("空字符串的数量为: " + count);

		count = strings.stream().filter(string -> string.length() == 3).count();
		System.out.println("字符串长度为 3 的数量为: " + count);
		
		filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选后的列表: " + filtered);
	}

	private static int getCountEmptyStringUsingJava7(List<String> strings) {
		int count = 0;

		for (String string : strings) {

			if (string.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
		List<String> filteredList = new ArrayList<String>();

		for (String string : strings) {

			if (!string.isEmpty()) {
				filteredList.add(string);
			}
		}
		return filteredList;
	}
}
