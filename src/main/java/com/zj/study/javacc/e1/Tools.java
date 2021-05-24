package com.zj.study.javacc.e1;

import java.util.List;
import java.util.Map;

public class Tools {
	static void print(Map<String, Object> map) {
		map.forEach((k, v) -> {
			System.out.print(k + ":");
			if (v instanceof List) {
				System.out.println();
				List l = (List) v;
				l.forEach(item -> {
					System.out.println(item);
				});
			} else {
				System.out.println(v.toString());
			}
		});
	}
}
