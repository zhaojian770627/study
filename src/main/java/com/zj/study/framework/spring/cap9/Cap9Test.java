package com.zj.study.framework.spring.cap9;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap9.config.Cap9MainConfig;
import com.zj.study.framework.spring.cap9.dao.TestDao;
import com.zj.study.framework.spring.cap9.service.TestService;

public class Cap9Test {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap9MainConfig.class);

		String[] names = app.getBeanDefinitionNames();

		for (String name : names) {
			System.out.println(name);
		}
		TestService testService = app.getBean(TestService.class);
		testService.println();
		// // 直接从容器中获取TestDao, 和使用Autowired注解来取比较
		TestDao testDao = (TestDao) app.getBean("testDao2");
		System.out.println(testDao);

		app.close();

	}
}
