package com.zj.study.framework.spring.lock.redisson.common;

import java.io.Serializable;

public class ExecutorResult<T> implements Serializable {
	boolean isSuccess = true;
	T data;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
