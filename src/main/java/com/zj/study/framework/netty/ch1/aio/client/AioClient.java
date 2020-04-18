package com.zj.study.framework.netty.ch1.aio.client;

import java.io.IOException;
import java.util.Scanner;

import com.zj.study.framework.netty.ch1.Ch01Const;

/**
 * AIO客户端
 * 
 * @author zhaojianc
 *
 */
public class AioClient {

	private static AioClientHandler clientHandle;

	private static void start() throws IOException {
		if (clientHandle != null)
			return;

		clientHandle = new AioClientHandler(Ch01Const.DEFAULT_SERVER_IP, Ch01Const.DEFAULT_PORT);
		// 负责网络通讯的线程
		new Thread(clientHandle, "Client").start();
	}

	private static boolean sendMsg(String msg) {
		if ("q".equals(msg))
			return false;

		clientHandle.sendMessag(msg);
		return true;
	}

	public static void main(String[] args) throws IOException {
		AioClient.start();
		System.out.println("请输入请求消息：");
		Scanner scanner = new Scanner(System.in);
		while (AioClient.sendMsg(scanner.nextLine()))
			;
	}

}
