package com.zj.study.netty.protobuf;

import com.zj.study.netty.protobuf.DataInfo.Message.MType;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {
		if (msg.getMType().equals(MType.StudentType)) {
			System.out.println(msg.getStudent());
		} else {
			System.out.println(msg.getTeacher());
		}

	}

}
