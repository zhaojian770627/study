package com.zj.study.framework.spring.lock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zj.study.framework.spring.lock.mdd.LockFacade;

//配置类====配置文件
@Configuration()
@PropertySource(value = "classpath:/redis.properties")
public class MainConfig {

	@Bean
	public LockFacade lockFacade() {
		return new LockFacade();
	}
}
