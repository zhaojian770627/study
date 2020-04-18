package com.zj.study.framework.netty.ch1.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.zj.study.framework.netty.ch1.Ch01Const;

public class AioReadHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel channel;

	public AioReadHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer byteBuffer) {
		// 如果条件成立，说明客户端主动终止了TCP套接字，这时服务端终止就可以了
		if (result == -1) {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		// flip操作
		byteBuffer.flip();
		byte[] message = new byte[byteBuffer.remaining()];
		byteBuffer.get(message);
		try {
			System.out.println(result);
			String msg = new String(message, "UTF-8");
			System.out.println("server accept message:" + msg);
			String responseStr = Ch01Const.response(msg);
			// 向客户端发送消息
			doWrite(responseStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doWrite(String result) {
		byte[] bytes = result.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();

		channel.write(writeBuffer, writeBuffer, new AioWriteHandler(channel));
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {

	}

}
