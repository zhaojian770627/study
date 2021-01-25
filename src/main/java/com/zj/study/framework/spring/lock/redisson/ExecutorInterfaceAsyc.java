package com.zj.study.framework.spring.lock.redisson;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

import com.zj.study.framework.spring.lock.redisson.task.TaskContext;

@RRemoteAsync(ExecutorInterface.class)
public interface ExecutorInterfaceAsyc {
	<T, V> RFuture<ExecutorResult<T>> execute(TaskContext taskContext, V o);
}
