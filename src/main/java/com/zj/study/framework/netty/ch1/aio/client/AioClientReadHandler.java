package com.zj.study.framework.netty.ch1.aio.client;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioClientReadHandler implements CompletionHandler {

	public AioClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void completed(Object result, Object attachment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failed(Throwable exc, Object attachment) {
		// TODO Auto-generated method stub

	}

}
