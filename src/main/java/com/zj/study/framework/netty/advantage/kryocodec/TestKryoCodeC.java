package com.zj.study.framework.netty.advantage.kryocodec;

import java.util.HashMap;
import java.util.Map;

import com.zj.study.framework.netty.advantage.vo.Message;
import com.zj.study.framework.netty.advantage.vo.MessageHeader;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Mark老师 享学课堂 https://enjoy.ke.qq.com 往期课程和VIP课程咨询 依娜老师 QQ：2133576719
 *         类说明：序列化器的测试类
 */
public class TestKryoCodeC {

	public Message getMessage() {
		Message myMessage = new Message();
		MessageHeader myHeader = new MessageHeader();
		myHeader.setLength(123);
		myHeader.setSessionID(99999);
		myHeader.setType((byte) 1);
		myHeader.setPriority((byte) 7);
		Map<String, Object> attachment = new HashMap<String, Object>();
		for (int i = 0; i < 10; i++) {
			attachment.put("ciyt --> " + i, "lilinfeng " + i);
		}
		myHeader.setAttachment(attachment);
		myMessage.setMessageHeader(myHeader);
		myMessage.setBody("abcdefg-----------------------AAAAAA");
		return myMessage;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TestKryoCodeC testC = new TestKryoCodeC();

		for (int i = 0; i < 5; i++) {
			ByteBuf sendBuf = Unpooled.buffer();
			Message message = testC.getMessage();
			System.out.println("Encode:" + message + "[body ] " + message.getBody());
			KryoSerializer.serialize(message, sendBuf);
			Message decodeMsg = (Message) KryoSerializer.deserialize(sendBuf);
			System.out.println("Decode:" + decodeMsg + "<body > " + decodeMsg.getBody());
			System.out.println("-------------------------------------------------");
		}

	}

}
