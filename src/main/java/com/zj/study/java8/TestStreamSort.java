package com.zj.study.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestStreamSort {

	public static void main(String[] args) {
		List<String> lst = new ArrayList<>();
		lst.add("d");
		lst.add("c");
		lst.add("f");
		lst.add("a");
		List<String> collect = lst.stream().sorted().collect(Collectors.toList());
		collect.forEach(s -> {
			System.out.println(s);
		});
	}

}
