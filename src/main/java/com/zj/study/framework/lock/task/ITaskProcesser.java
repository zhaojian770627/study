package com.zj.study.framework.lock.task;

public interface ITaskProcesser<T, R> {
	TaskResult<R> taskExecute(T data);
}
