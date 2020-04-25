package com.zj.study.framework.netty.ch2.linebase;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 
 * 演示粘包和半包
 * 
 * @author zhaojianc
 *
 */
public class EchoClient {
	private final int port;
	private final String host;

	private void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();/* 线程组 */
		try {
			Bootstrap b = new Bootstrap();/* 客户端启动必备 */
			b.group(group).channel(NioSocketChannel.class)/* 指明使用NIO进行网络通讯 */
					.remoteAddress(new InetSocketAddress(host, port))/* 配置远程服务器的地址 */
					.handler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							// 使用系统换行符
							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
							ch.pipeline().addLast(new EchoClientHandler());
						}
					});
			ChannelFuture f = b.connect().sync();/* 连接到远程节点，阻塞等待直到连接完成 */
			/* 阻塞，直到channel关闭 */
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}

	public EchoClient(int port, String host) {
		this.port = port;
		this.host = host;
	}

	public static void main(String[] args) throws InterruptedException {
		new EchoClient(9999, "127.0.0.1").start();
	}
}
