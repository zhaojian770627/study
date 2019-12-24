package com.zj.study.framework.spring.cap2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.zj.study.framework.spring.cap1.Person;

@Configuration
@ComponentScan(value = "com.zj.study.framework.spring.cap2", includeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = { JamesTypeFilter.class }) }, useDefaultFilters = false)

public class Cap2MainConfig {
	@Bean
	public Person person01() {
		return new Person("james", 20);
	}
}
