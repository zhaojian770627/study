package com.zj.study.framework.mybatis.small.batis.reflection;

import java.lang.reflect.Field;

public class ReflectionUtil {

	public static void setPropToBean(Object bean, String propName, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f;
		f = bean.getClass().getDeclaredField(propName);
		f.setAccessible(true);
		f.set(bean, value);
	}

}
