package com.zj.study.framework.spring.lock.redisson.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RRemoteService;
import org.redisson.api.RemoteInvocationOptions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;
import com.zj.study.framework.spring.lock.redisson.common.ExecutorResult;
import com.zj.study.framework.spring.lock.redisson.task.ITaskRecord;
import com.zj.study.framework.spring.lock.redisson.task.TaskContext;

public class RedissonExecutorClientMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");

		RRemoteService remoteService = redissonClient.getRemoteService();
		ExecutorInterfaceAsyc service1 = remoteService.get(ExecutorInterfaceAsyc.class,
				RemoteInvocationOptions.defaults().noAck().expectResultWithin(1, TimeUnit.HOURS));
		ExecutorInterfaceAsyc service2 = remoteService.get(ExecutorInterfaceAsyc.class);

		// 登记任务
		ITaskRecord taskRecord = app.getBean(ITaskRecord.class);

		TaskContext task1 = new TaskContext("task1", "任务1");
		TaskContext task2 = new TaskContext("task2", "任务2");
		TaskContext task3 = new TaskContext("task3", "任务2");

		TaskContext[] tasks = new TaskContext[] { task1, task2, task3 };
//		TaskContext[] tasks = new TaskContext[] { task1 };
		taskRecord.register(Arrays.asList(tasks));

		List<RFuture<ExecutorResult<Integer>>> futures = new ArrayList<>();
		for (TaskContext task : tasks) {
//			ExecutorInterfaceAsyc service1 = remoteService.get(ExecutorInterfaceAsyc.class);
			RFuture<ExecutorResult<Integer>> future = service1.execute(task, 2);
			futures.add(future);

		}

//		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		for (RFuture<ExecutorResult<Integer>> future : futures) {
//			ExecutorResult<Integer> result = future.get();
			ExecutorResult<Integer> result = future.get();
			System.out.println(result.isSuccess() == true ? result.getData() : null);
		}
		app.close();
		System.exit(0);
	}

}
