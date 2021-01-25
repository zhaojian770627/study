package com.zj.study.framework.spring.lock.redisson.task;

import java.util.List;

public interface ITaskRecord {

	void register(List<TaskContext> asList);

	void setStart(TaskContext taskContext);

	void setSuccess(TaskContext taskContext);

	void failed(TaskContext taskContext, String message);

}
