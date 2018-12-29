package com.zj.study.netty.three;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
