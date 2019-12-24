package com.zj.study.framework.spring.cap3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.zj.study.framework.spring.cap1.Person;

@Configuration
public class Cap3MainConfig {
	@Scope("prototype")
	@Bean
	public Person person(){
		return new Person("james",20);
	}
}
