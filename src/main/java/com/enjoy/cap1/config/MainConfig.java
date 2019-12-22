package com.enjoy.cap1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zj.study.framework.spring.cap1.Person;

//������====�����ļ�
@Configuration
public class MainConfig {
	@Bean("james")
	public Person person() {
		return new Person("james", 20);
	}
}
