package com.zj.study.netty.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {

	public static void main(String[] args) throws InterruptedException, IOException {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
			Channel channel = channelFuture.channel();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			for (;;) {
				String readStr = br.readLine();
				if ("quit".equals(readStr))
					System.exit(0);

				channel.writeAndFlush(readStr + "\r\n");
			}
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
