package com.zj.study.framework.spring.lock.redisson.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteProxy<T> implements InvocationHandler {

	private final Class<T> mapperInterface;

	public RemoteProxy(Class<T> mapperInterface) {
		super();
		this.mapperInterface = mapperInterface;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {// 如果是Object本身的方法不增强
			return method.invoke(this, args);
		}

		Class<?> returnType = method.getReturnType();// 获取方法的返回参数class对象

		Object ret = null;

		return ret;
	}

}
