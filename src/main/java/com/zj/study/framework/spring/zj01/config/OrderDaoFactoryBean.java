package com.zj.study.framework.spring.zj01.config;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.zj.study.framework.spring.zj01.dao.IOrderDao;

public class OrderDaoFactoryBean implements FactoryBean<IOrderDao> {

	@Override
	public IOrderDao getObject() throws Exception {
		return (IOrderDao) Proxy.newProxyInstance(IOrderDao.class.getClassLoader(), new Class[] { IOrderDao.class },
				new OrderDaoProxy());
	}

	@Override
	public Class<?> getObjectType() {
		return IOrderDao.class;
	}

}
