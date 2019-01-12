package com.zj.study.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {
		if (msg.getMType().equals(0)) {
			System.out.println(msg.getStudent());
		} else {
			System.out.println(msg.getTeacher());
		}

	}

}
