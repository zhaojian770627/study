package com.zj.study.framework.lock;

import java.util.LinkedList;
import java.util.UUID;

/**
 * @author Mark老师 享学课堂 https://enjoy.ke.qq.com
 *
 *         类说明：实现一个数据库的连接池
 */
public class DBPool {

	// 资源池的容器
	private static LinkedList<String> resourcePool = new LinkedList<>();

	public DBPool(int initalSize) {
		if (initalSize > 0) {
			for (int i = 0; i < initalSize; i++) {
				resourcePool.addLast(UUID.randomUUID().toString());
			}
		}
	}

	// 在mills时间内还拿不到数据库连接，返回一个null
	public String fetchConn(long mills) throws InterruptedException {
		synchronized (resourcePool) {
			if (mills < 0) {
				while (resourcePool.isEmpty()) {
					resourcePool.wait();
				}
				return resourcePool.removeFirst();
			} else {
				long overtime = System.currentTimeMillis() + mills;
				long remain = mills;
				while (resourcePool.isEmpty() && remain > 0) {
					resourcePool.wait(remain);
					remain = overtime - System.currentTimeMillis();
				}
				String result = null;
				if (!resourcePool.isEmpty()) {
					result = resourcePool.removeFirst();
				}
				return result;
			}
		}
	}

	// 放回数据库连接
	public void releaseConn(String conn) {
		if (conn != null) {
			synchronized (resourcePool) {
				resourcePool.addLast(conn);
				resourcePool.notifyAll();
			}
		}
	}

}
