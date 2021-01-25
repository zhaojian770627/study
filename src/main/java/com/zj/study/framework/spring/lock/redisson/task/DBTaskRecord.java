package com.zj.study.framework.spring.lock.redisson.task;

import java.util.List;

public class DBTaskRecord implements ITaskRecord {

	@Override
	public void register(List<TaskContext> taskContexts) {
		for (TaskContext taskContext : taskContexts) {
			System.err.println(String.format("Id[%s] Name[%s] Registed", taskContext.getId(), taskContext.getName()));
		}
	}

	@Override
	public void setStart(TaskContext taskContext) {
		System.err.println(String.format("Id[%s] Name[%s] Started", taskContext.getId(), taskContext.getName()));
	}

	@Override
	public void setSuccess(TaskContext taskContext) {
		System.err.println(String.format("Id[%s] Name[%s] Completed", taskContext.getId(), taskContext.getName()));
	}

	@Override
	public void failed(TaskContext taskContext, String message) {
		System.err.println(String.format("Id[%s] Name[%s] failed", taskContext.getId(), taskContext.getName()));
	}

}
