package com.zj.study.framework.spring.cap12.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.zj.study.framework.spring.cap9.bean.Moon;

@Configurable
@ComponentScan("com.zj.study.framework.spring.cap12")
public class Cap12MainConfig {
	@Bean
	public Moon getMoon() {
		return new Moon();
	}
}
