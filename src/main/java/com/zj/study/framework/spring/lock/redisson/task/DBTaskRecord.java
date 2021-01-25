package com.zj.study.framework.spring.lock.redisson.task;

import java.util.List;

public class DBTaskRecord implements ITaskRecord {

	@Override
	public void register(List<TaskContext> taskContexts) {
		for (TaskContext taskContext : taskContexts) {
			System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Registed", System.currentTimeMillis(),
					taskContext.getId(), taskContext.getName()));
		}
	}

	@Override
	public void setStart(TaskContext taskContext) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Started", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
		System.err.flush();
	}

	@Override
	public void setSuccess(TaskContext taskContext) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Completed", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
	}

	@Override
	public void failed(TaskContext taskContext, String message) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] failed", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
	}

}
