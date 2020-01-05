package com.zj.study.framework.spring.cap12.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ZJBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("ZJBeanFactoryPostProcessor...postProcessBeanFactory");

		// 所有的Bean的定义，已经加载到beanFactory,但是Bean还没有创建
		int count = beanFactory.getBeanDefinitionCount();
		String[] beanDefNames = beanFactory.getBeanDefinitionNames();
		System.out.println("当前BeanFactory中有" + count + "个Bean");
		for (String beanDefName : beanDefNames) {
			System.out.println(beanDefName);
		}
	}
}
