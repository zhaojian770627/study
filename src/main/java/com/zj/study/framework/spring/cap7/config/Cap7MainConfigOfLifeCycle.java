package com.zj.study.framework.spring.cap7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zj.study.framework.spring.cap1.Person;
import com.zj.study.framework.spring.cap7.bean.Bike;

@ComponentScan("com.zj.study.framework.spring.cap7.bean")
@Configuration
public class Cap7MainConfigOfLifeCycle {
	@Bean("person")
	public Person person() {
		System.out.println("给容器中添加person.......");
		return new Person("person", 20);
	}

	@Bean(initMethod = "init", destroyMethod = "destory")
	public Bike bike() {
		return new Bike();
	}

}
