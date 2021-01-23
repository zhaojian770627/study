package com.zj.study.framework.spring.lock.redisson;

import org.springframework.context.ApplicationContext;

public class ExecutorInterfaceImpl implements ExecutorInterface {

	final String flag;
	private ApplicationContext appContext;

	public ExecutorInterfaceImpl(String flag, ApplicationContext appContext) {
		super();
		this.flag = flag;
		this.appContext = appContext;
	}

	@Override
	public <T, V> ExecutorResult<T> execute(String type, V v) {
		ExecutorResult<T> executorResult = new ExecutorResult<T>();
		try {
			T r = innerExec(type, v);
			executorResult.setSuccess(true);
			executorResult.setData(r);
		} catch (Throwable e) {
			executorResult.setSuccess(false);
		}
		return executorResult;
	}

	private <V, T> T innerExec(String type, V v) {
		Integer vi = (Integer) v;
		if (vi == 1)
			throw new RuntimeException("123");
		return (T) vi;
	}
}
