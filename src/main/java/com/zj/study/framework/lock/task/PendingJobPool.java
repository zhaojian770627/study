package com.zj.study.framework.lock.task;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PendingJobPool {
	private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();
	private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);

	private static ExecutorService taskExecutor = new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60,
			TimeUnit.SECONDS, taskQueue);

	// Job的容器
	private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

	private PendingJobPool() {
	}

	private static class JobPoolHolder {
		public static PendingJobPool pool = new PendingJobPool();
	}

	public static PendingJobPool getInstance() {
		return JobPoolHolder.pool;
	}

	public <R> void registerJob(String jobName, int jobLength, ITaskProcesser<?, ?> taskProcesser, long expireTime) {
		JobInfo<R> jobInfo = new JobInfo<>(jobName, jobLength, taskProcesser, expireTime);
		if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
			throw new RuntimeException(jobName + "已经注册了!");
		}
	}

	private <R> JobInfo<R> getJobInfo(String jobName) {
		JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
		if (null == jobInfo)
			throw new RuntimeException(jobName + " 非法任务!");

		return jobInfo;
	}

	public <R> List<TaskResult<R>> getTaskDetail(String jobName) {
		JobInfo<R> jobInfo = getJobInfo(jobName);
		return jobInfo.getTaskDetail();
	}

	public <R> String getTaskProgess(String jobName) {
		JobInfo<R> jobInfo = getJobInfo(jobName);
		return jobInfo.getTotalProcess();
	}

	public <T, R> void putTask(String jobName, T t) {
		JobInfo<R> jobInfo = getJobInfo(jobName);
		PendingTask<T, R> task = new PendingTask<T, R>(jobInfo, t);
		taskExecutor.execute(task);
	}

	public void clear(String jobName) {
		jobInfoMap.remove(jobName);
	}

	private static class PendingTask<T, R> implements Runnable {

		private JobInfo<R> jobInfo;
		private T processData;

		public PendingTask(JobInfo<R> jobInfo, T processData) {
			super();
			this.jobInfo = jobInfo;
			this.processData = processData;
		}

		@Override
		public void run() {
			ITaskProcesser<T, R> taskProcessor = (ITaskProcesser<T, R>) jobInfo.getTaskRrocesser();
			TaskResult<R> result = null;
			System.out.println("PendingTask execute");
			try {
				result = taskProcessor.taskExecute(processData);
				if (null == result) {
					result = new TaskResult<R>(TaskResultType.Exception, null, "result is null");
				}

				if (result.getResultType() == null) {
					result = new TaskResult<R>(TaskResultType.Exception, null,
							"result is null" + null == result.getReason() ? ",reason is null" : "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = new TaskResult<R>(TaskResultType.Exception, null, "result is null");
			} finally {
				jobInfo.addTaskResult(result);
			}
			System.out.println("PendingTask complete");
		}

	}
}
