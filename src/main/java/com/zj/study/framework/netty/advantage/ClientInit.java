package com.zj.study.framework.netty.advantage;

import com.zj.study.framework.netty.advantage.kryocodec.KryoDecoder;
import com.zj.study.framework.netty.advantage.kryocodec.KryoEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ClientInit extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		/* 剥离接收到的消息的长度字段，拿到实际的消息报文的字节数组 */
		ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));

		/* 给发送出去的消息增加长度字段 */
		ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));

		/* 反序列化，将字节数组转换为消息实体 */
		ch.pipeline().addLast(new KryoDecoder());

		/* 序列化，将消息实体转换为字节数组准备进行网络传输 */
		ch.pipeline().addLast("MessageEncoder", new KryoEncoder());
	}

}
