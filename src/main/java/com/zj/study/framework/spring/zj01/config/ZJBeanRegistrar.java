package com.zj.study.framework.spring.zj01.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.zj.study.framework.spring.zj01.dao.IOrderDao;

public class ZJBeanRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(IOrderDao.class);
		registry.registerBeanDefinition("orderDao", bd);
	}

}
