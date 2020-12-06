package com.zj.study.framework.spring.lock.redisson;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

@RRemoteAsync(ExecutorInterface.class)
public interface ExecutorInterfaceAsyc {
	<T,V> RFuture<T> executor(String type, V o);
}
