package com.zj.study.framework.netty.ch1.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AioServerHandler implements Runnable {
	public CountDownLatch latch;
	public AsynchronousServerSocketChannel channel;

	public AioServerHandler(int port) throws IOException {
		channel = AsynchronousServerSocketChannel.open();
		// 绑定端口
		channel.bind(new InetSocketAddress(port));
		System.out.println("Server is start,port:" + port);
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		// 用于接收客户端的连接，异步操作，
		// 需要实现了CompletionHandler接口的处理器处理和客户端的连接操作
		channel.accept(this, new AioAcceptHandler());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
