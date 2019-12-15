package com.zj.study.framework.lock.task;

import java.util.concurrent.DelayQueue;

import com.zj.study.framework.lock.delay.ItemVO;

public class CheckJobProcesser {
	private static DelayQueue<ItemVO<String>> queue = new DelayQueue<>();

	private CheckJobProcesser() {
	}

	private static class ProcesserHolder {
		public static CheckJobProcesser processer = new CheckJobProcesser();
	}

	public static CheckJobProcesser getInstance() {
		return ProcesserHolder.processer;
	}

	public void putJob(String jobName, long expireTime) {
		ItemVO<String> item = new ItemVO<String>(expireTime, jobName);
		queue.offer(item);
		System.out.println("Job[" + jobName + "已经放入了过期检查缓存，过期时长:" + expireTime);
	}

	static {
		Thread thread = new Thread(new FetchJob());
		thread.setDaemon(true);
		thread.start();
		System.out.println("开启任务过期检查守护线程..................");
	}

	private static class FetchJob implements Runnable {

		@Override
		public void run() {
			try {
				ItemVO<String> item = queue.take();
				String jobName = item.getData();
				PendingJobPool.getInstance().clear(jobName);
				System.out.println(jobName + " is out of date,remove from map!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
