package com.zj.study.java8;

import java.util.ArrayList;
import java.util.List;

public class TestStreamSort {

	public static void main(String[] args) {
		List<String> lst = new ArrayList<>();
		lst.add("d");
		lst.add("c");
		lst.add("f");
		lst.add("a");
		lst.sort(null);
		lst.forEach(s -> {
			System.out.println(s);
		});
	}

}
