package com.zj.study.framework.spring.lock.redisson.common;

import com.zj.study.framework.spring.lock.redisson.task.TaskContext;

public interface ExecutorInterface {
	public <T, V> ExecutorResult<T> execute(TaskContext taskContext, V v);
}
