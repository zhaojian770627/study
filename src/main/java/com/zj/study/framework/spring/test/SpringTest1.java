package com.zj.study.framework.spring.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.study.framework.spring.cap1.Person;
import com.zj.study.framework.spring.cap1.config.MainConfig;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = MainConfig.class)
public class SpringTest1 {

	@Autowired
	Person person;

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(person);
	}

}
