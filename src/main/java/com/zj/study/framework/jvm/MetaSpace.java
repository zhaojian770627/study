package com.zj.study.framework.jvm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 演示方法区空间不足
 * 
 * -XX:MaxMetaspaceSize=1m
 * 
 * @author zhaojian
 *
 */
public class MetaSpace {

	public static void main(String[] args) {
		List<Object> list = new LinkedList<>();
		Map<String, Object> map = new HashMap<>();
		// int i = 0;
		// while (true) {
		// i++;
		// if (i % 1000 == 0)
		// System.out.println("i=" + i);
		// list.add(new Object());
		// }
	}

}
