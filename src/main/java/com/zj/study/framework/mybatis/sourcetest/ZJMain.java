package com.zj.study.framework.mybatis.sourcetest;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

public class ZJMain {
	public static void main(String[] args) {
		ObjectFactory objectFactory = new DefaultObjectFactory();
		ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
		ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
		User user = objectFactory.create(User.class);
		MetaObject metaObject = MetaObject.forObject(user, objectFactory, objectWrapperFactory, reflectorFactory);

		ObjectWrapper wraper = new BeanWrapper(metaObject, user);
		PropertyTokenizer prop = new PropertyTokenizer("name");
		wraper.set(prop, "zj");
		System.out.println(user);
	}
}
