package com.zj.study.framework.spring.cap10.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.zj.study.framework.spring.cap10.aop.Calculator;
import com.zj.study.framework.spring.cap10.aop.LogAspects;

@Configurable
@EnableAspectJAutoProxy
public class Cap10MainConfig {
	@Bean
	public Calculator calculator() {
		return new Calculator();
	}

	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
}
