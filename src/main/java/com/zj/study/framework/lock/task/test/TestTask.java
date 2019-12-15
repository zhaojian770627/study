package com.zj.study.framework.lock.task.test;

import java.util.Random;

import com.zj.study.framework.lock.SleepTools;
import com.zj.study.framework.lock.task.ITaskProcesser;
import com.zj.study.framework.lock.task.TaskResult;
import com.zj.study.framework.lock.task.TaskResultType;

public class TestTask implements ITaskProcesser<Integer, Integer> {

	@Override
	public TaskResult<Integer> taskExecute(Integer data) {
		Random r = new Random();
		int flag = r.nextInt();
		SleepTools.ms(flag);
		if (flag <= 300) {
			Integer retValue = data.intValue() + flag;
			return new TaskResult<Integer>(TaskResultType.Success, retValue);
		} else if (flag > 301 && flag <= 400) {
			return new TaskResult<Integer>(TaskResultType.Failure, -1, "Failure");
		} else {
			try {
				throw new RuntimeException("发生了异常!!!");
			} catch (Exception e) {
				return new TaskResult<Integer>(TaskResultType.Exception, -1, e.getMessage());
			}
		}

	}

}
