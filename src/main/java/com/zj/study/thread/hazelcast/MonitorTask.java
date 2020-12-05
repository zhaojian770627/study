package com.zj.study.thread.hazelcast;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class MonitorTask implements Callable<String>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String call() throws Exception {
		return "123";
	}
}
