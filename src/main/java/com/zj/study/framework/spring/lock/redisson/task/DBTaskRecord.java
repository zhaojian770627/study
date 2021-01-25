package com.zj.study.framework.spring.lock.redisson.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBTaskRecord implements ITaskRecord {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void register(List<TaskContext> taskContexts) {
		for (TaskContext taskContext : taskContexts) {
			System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Registed", System.currentTimeMillis(),
					taskContext.getId(), taskContext.getName()));
			jdbcTemplate.execute(String.format("insert into zj_task(id,name,memo,status) values('%s','%s','%s','%s')",
					taskContext.getId(), taskContext.getName(), taskContext.getDesc(), "registered"));
		}
	}

	@Override
	public void setStart(TaskContext taskContext) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Started", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
		jdbcTemplate.execute("update zj_task set status='started' where id='" + taskContext.getId() + "'");
	}

	@Override
	public void setSuccess(TaskContext taskContext) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] Completed", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
		jdbcTemplate.execute("update zj_task set status='success' where id='" + taskContext.getId() + "'");
	}

	@Override
	public void failed(TaskContext taskContext, String message) {
		System.err.println(String.format("CurTime [%d] Id[%s] Name[%s] failed", System.currentTimeMillis(),
				taskContext.getId(), taskContext.getName()));
		jdbcTemplate.execute("update zj_task set status='failed' where id='" + taskContext.getId() + "'");
	}

}
