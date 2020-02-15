package com.zj.study.framework.mybatis.small.batis.binding;

import java.lang.reflect.Proxy;

import com.zj.study.framework.mybatis.small.batis.session.SqlSession;

public class MapperProxyFactory {

	public static <T> T getMapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface);
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface },
				mapperProxy);
	}

}
