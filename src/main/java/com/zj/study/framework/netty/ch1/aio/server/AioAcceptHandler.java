package com.zj.study.framework.netty.ch1.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerHandler> {

	@Override
	public void completed(AsynchronousSocketChannel channel, AioServerHandler serverHandler) {
		AioServer.clientCount++;
		System.out.println("连接的客户端数：" + AioServer.clientCount);
		serverHandler.channel.accept(serverHandler, this);
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		channel.read(readBuffer, readBuffer, new AioReadHandler(channel));
	}

	@Override
	public void failed(Throwable exc, AioServerHandler serverHandler) {
		exc.printStackTrace();
		serverHandler.latch.countDown();
	}
}
