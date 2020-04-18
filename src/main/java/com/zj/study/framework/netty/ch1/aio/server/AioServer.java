package com.zj.study.framework.netty.ch1.aio.server;

import com.zj.study.framework.netty.ch1.Ch01Const;

public class AioServer {
	private static AioServerHandler serverHandle;
	// 统计客户端个数
	public volatile static long clientCount = 0;

	private static void start() {
		if (serverHandle != null)
			return;
		serverHandle = new AioServerHandler(Ch01Const.DEFAULT_PORT);
		new Thread(serverHandle, "Server").start();
	}

	public static void main(String[] args) {
		AioServer.start();
	}

}
