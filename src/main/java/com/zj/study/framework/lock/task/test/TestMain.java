package com.zj.study.framework.lock.task.test;

import java.util.List;
import java.util.Random;

import com.zj.study.framework.lock.SleepTools;
import com.zj.study.framework.lock.task.PendingJobPool;
import com.zj.study.framework.lock.task.TaskResult;

public class TestMain {

	private final static String JOB_NAME = "计算数值";
	private final static int JOB_LENGTH = 1000;

	private static class QueryResult implements Runnable {
		private PendingJobPool pool;

		public QueryResult(PendingJobPool pool) {
			super();
			this.pool = pool;
		}

		@Override
		public void run() {
			int i = 0;
			while (i < 350) {
				List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);

				if (!taskDetail.isEmpty()) {
					System.out.println(pool.getTaskProgess(JOB_NAME));
					System.out.println(taskDetail);
				}
				SleepTools.ms(100);
				i++;

			}
		}

	}

	public static void main(String[] args) {
		TestTask testTask = new TestTask();
		PendingJobPool pool = PendingJobPool.getInstance();
		pool.registerJob(JOB_NAME, JOB_LENGTH, testTask, 1000 * 5);

		Random r = new Random();
		for (int i = 0; i < JOB_LENGTH; i++) {
			System.out.println("put task " + i);
			pool.putTask(JOB_NAME, r.nextInt(1000));
		}
		Thread t = new Thread(new QueryResult(pool));
		t.start();
		
		SleepTools.ms(100000);
	}
}
