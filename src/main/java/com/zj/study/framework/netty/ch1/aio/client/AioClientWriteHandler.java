package com.zj.study.framework.netty.ch1.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel clientChannel;
	private CountDownLatch latch;

	public AioClientWriteHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
		this.clientChannel = clientChannel;
		this.latch = latch;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		if (buffer.hasRemaining()) {
			clientChannel.write(buffer, buffer, this);
		} else {
			// 写操作已经完成，为读取服务端传回的数据建立缓冲区
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			/*
			 * 这个方法会迅速返回，需要提供一个接口让 系统在读操作完成后通知我们的应用程序。
			 */
			clientChannel.read(readBuffer, readBuffer, new AioClientReadHandler(clientChannel, latch));
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		System.err.println("数据发送失败...");
		try {
			clientChannel.close();
			latch.countDown();
		} catch (IOException e) {
		}
	}
}
