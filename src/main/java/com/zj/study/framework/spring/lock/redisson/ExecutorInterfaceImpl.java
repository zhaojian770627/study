package com.zj.study.framework.spring.lock.redisson;

public class ExecutorInterfaceImpl<T, V> implements ExecutorInterface {

	final String flag;

	public ExecutorInterfaceImpl(String flag) {
		super();
		this.flag = flag;
	}

	@Override
	public <T, V> T executor(String type, V o) {
		T t = (T) o;
		System.err.println(String.format("服务器[%s]执行任务[%s]", flag, o.toString()));
		return t;
	}

}
