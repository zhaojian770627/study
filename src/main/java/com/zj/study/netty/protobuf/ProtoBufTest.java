package com.zj.study.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		DataInfo.Student s1 = DataInfo.Student.newBuilder().setName("赵建").setAge(20).setAddress("beijing").build();
		byte[] student2ByteArray = s1.toByteArray();
		DataInfo.Student s2 = DataInfo.Student.parseFrom(student2ByteArray);
		System.out.println(s2);
	}

}
