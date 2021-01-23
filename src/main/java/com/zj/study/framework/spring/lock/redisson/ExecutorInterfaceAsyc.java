package com.zj.study.framework.spring.lock.redisson;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

@RRemoteAsync(ExecutorInterface.class)
public interface ExecutorInterfaceAsyc {
	<T, V> RFuture<ExecutorResult<T>> execute(String type, V o);
}
