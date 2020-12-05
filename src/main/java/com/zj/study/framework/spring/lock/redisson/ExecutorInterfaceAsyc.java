package com.zj.study.framework.spring.lock.redisson;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

@RRemoteAsync(ExecutorInterface.class)
public interface ExecutorInterfaceAsyc {
	RFuture<String> executor(String type, Object o);
}
