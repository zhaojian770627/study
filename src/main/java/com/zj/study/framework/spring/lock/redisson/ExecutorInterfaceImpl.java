package com.zj.study.framework.spring.lock.redisson;

import java.util.UUID;

public class ExecutorInterfaceImpl<T, V> implements ExecutorInterface {

	String flag = null;

	public ExecutorInterfaceImpl() {
		super();
		this.flag = UUID.randomUUID().toString();
	}

	@Override
	public <T, V> T executor(String type, V o) {
		T t = (T) o;
		System.err.println(flag);
		return t;
	}

}
