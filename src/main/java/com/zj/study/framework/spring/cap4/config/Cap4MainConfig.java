package com.zj.study.framework.spring.cap4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.zj.study.framework.spring.cap1.Person;

@Configuration
public class Cap4MainConfig {
	@Lazy
	@Bean
	public Person person(){
		System.out.println("正在构造person.......");
		return new Person("james",20);
	}
}
