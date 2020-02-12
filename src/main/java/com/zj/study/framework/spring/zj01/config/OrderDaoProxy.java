package com.zj.study.framework.spring.zj01.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.ibatis.reflection.ExceptionUtil;

public class OrderDaoProxy implements InvocationHandler {

	public OrderDaoProxy() {
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			try {
				return method.invoke(this, args);
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		}
		System.err.println("Execute proxy method");
		return null;
	}
}