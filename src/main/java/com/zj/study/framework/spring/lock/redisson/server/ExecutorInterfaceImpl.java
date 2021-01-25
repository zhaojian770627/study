package com.zj.study.framework.spring.lock.redisson.server;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;

import com.zj.study.framework.spring.lock.redisson.common.ExecutorInterface;
import com.zj.study.framework.spring.lock.redisson.common.ExecutorResult;
import com.zj.study.framework.spring.lock.redisson.task.ITaskRecord;
import com.zj.study.framework.spring.lock.redisson.task.TaskContext;

public class ExecutorInterfaceImpl implements ExecutorInterface {

	final String flag;
	private ApplicationContext appContext;
	private ITaskRecord taskRecord;

	public ExecutorInterfaceImpl(String flag, ApplicationContext appContext) {
		super();
		this.flag = flag;
		this.appContext = appContext;
		taskRecord = this.appContext.getBean(ITaskRecord.class);
	}

	@Override
	public <T, V> ExecutorResult<T> execute(TaskContext taskContext, V v) {
		ExecutorResult<T> executorResult = new ExecutorResult<T>();
		try {
			taskRecord.setStart(taskContext);
			T r = innerExec(taskContext, v);
			executorResult.setSuccess(true);
			executorResult.setData(r);
			taskRecord.setSuccess(taskContext);
		} catch (Throwable e) {
			executorResult.setSuccess(false);
			taskRecord.failed(taskContext, e.getMessage());
		}
		return executorResult;
	}

	private <V, T> T innerExec(TaskContext typetaskContext, V v) throws InterruptedException {
		Integer vi = (Integer) v;
		TimeUnit.SECONDS.sleep(5);
		if (vi == 1)
			throw new RuntimeException("123");
		return (T) vi;
	}
}
