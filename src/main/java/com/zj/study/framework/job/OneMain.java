package com.zj.study.framework.job;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OneMain {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/com/zj/study/framework/job/job.xml");

		System.in.read();
	}

}
