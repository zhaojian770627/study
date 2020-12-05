package com.zj.study.framework.spring.lock.redisson;

import java.util.UUID;

public class ExecutorInterfaceImpl implements ExecutorInterface {

	String flag = null;

	public ExecutorInterfaceImpl() {
		super();
		this.flag = UUID.randomUUID().toString();
	}

	@Override
	public String executor(String type, Object o) {
		return flag;
	}

}
