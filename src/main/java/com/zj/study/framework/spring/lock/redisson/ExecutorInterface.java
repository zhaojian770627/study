package com.zj.study.framework.spring.lock.redisson;

public interface ExecutorInterface {
	public <T, V> ExecutorResult<T> execute(String type, V v);
}
