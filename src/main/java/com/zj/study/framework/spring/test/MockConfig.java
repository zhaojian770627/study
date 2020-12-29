package com.zj.study.framework.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockConfig {
	@Bean
	public AddressService addressService() {
		return new AddressServiceImpl();
	}
	
	
	@Bean
	public StudentService studentService() {
		return new StudentServiceImpl();
	}
}
