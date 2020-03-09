package com.zj.study.framework.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * -Xms:500m  -Xmx:500m
 * 演示堆溢出
 * 
 * @author zhaojian
 *
 */
public class OOM {

	public static void main(String[] args) {

		//-----------  -Xms5m -Xmx5m -XX:+PrintGC
		List<Object> list = new LinkedList<>();
		int i = 0;
		while (true) {
			i++;
			if (i % 1000 == 0)
				System.out.println("i=" + i);
			list.add(new Object());
		}
		//-----------------------------------------
		
		// -------------巨型数组
//		String[] sary=new String[100000000]; 
		
	}

}
