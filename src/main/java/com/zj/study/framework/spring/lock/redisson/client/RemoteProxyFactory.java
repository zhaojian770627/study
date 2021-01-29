package com.zj.study.framework.spring.lock.redisson.client;

import java.lang.reflect.Proxy;

import com.zj.study.framework.mybatis.small.batis.session.SqlSession;

public class RemoteProxyFactory {

	public static <T> T getMapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		RemoteProxy<T> mapperProxy = new RemoteProxy<T>(mapperInterface);
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface },
				mapperProxy);
	}

}
