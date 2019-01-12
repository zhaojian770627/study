package com.zj.study.protobuf;

import java.util.Random;

import com.google.protobuf.MessageLite;
import com.zj.study.protobuf.DataInfo.Message.MType;
import com.zj.study.protobuf.DataInfo.Student;
import com.zj.study.protobuf.DataInfo.Teacher;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		int randomInt = new Random().nextInt(2);
		DataInfo.Message msg;
		if (0 == randomInt) {
			msg = DataInfo.Message.newBuilder().setMType(MType.StudentType)
					.setStudent(Student.newBuilder().setName("zhaojian").setAge(41).setAddress("beijing").build())
					.build();
		} else {
			msg = DataInfo.Message.newBuilder().setMType(MType.TeacherType)
					.setTeacher(Teacher.newBuilder().setName("zhaojian").setAge(41).setCourse(3).build()).build();
		}
		ctx.writeAndFlush(msg);
	}
}
