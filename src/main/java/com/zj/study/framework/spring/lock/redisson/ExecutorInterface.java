package com.zj.study.framework.spring.lock.redisson;

public interface ExecutorInterface {
	<T, V> T executor(String type, V o);
}
