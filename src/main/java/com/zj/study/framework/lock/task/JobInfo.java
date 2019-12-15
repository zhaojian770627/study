package com.zj.study.framework.lock.task;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class JobInfo<R> {
	// 区分唯一的工作
	private final String jobName;

	private final int jobLength;
	// 工作任务处理器
	private final ITaskProcesser<?, ?> taskRrocesser;

	private AtomicInteger successCount;
	private AtomicInteger taskProcessedCount;
	private LinkedBlockingDeque<TaskResult<R>> taskDetailQueue;
	private final long expireTime;

	public JobInfo(String jobName, int jobLength, ITaskProcesser<?, ?> taskRrocesser, long expireTime) {
		super();
		this.jobName = jobName;
		this.jobLength = jobLength;
		this.taskRrocesser = taskRrocesser;

		this.successCount = new AtomicInteger(0);
		this.taskProcessedCount = new AtomicInteger(0);

		this.taskDetailQueue = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
		this.expireTime = expireTime;
	}

	public ITaskProcesser<?, ?> getTaskRrocesser() {
		return taskRrocesser;
	}

	public int getSuccessCount() {
		return successCount.get();
	}

	public int getTaskProcessedCount() {
		return taskProcessedCount.get();
	}

	public List<TaskResult<R>> getTaskDetail() {
		List<TaskResult<R>> taskList = new LinkedList<>();
		TaskResult<R> taskResult;
		while ((taskResult = taskDetailQueue.pollFirst()) != null) {
			taskList.add(taskResult);
		}
		return taskList;
	}

	public void addTaskResult(TaskResult<R> result) {
		if (TaskResultType.Success.equals(result.getResultType()))
			successCount.incrementAndGet();

		taskDetailQueue.addLast(result);
		taskProcessedCount.incrementAndGet();

		if (taskProcessedCount.get() == jobLength) {
			CheckJobProcesser.getInstance().putJob(jobName, expireTime);
		}
	}

	public String getTotalProcess() {
		return "Success[" + successCount.get() + "]/Current[" + taskProcessedCount.get() + "] Total[" + jobLength + "]";
	}
}
