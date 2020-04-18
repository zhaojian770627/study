package com.zj.study.framework.netty.ch1.aio.client;

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

	private static void start() {
		if (clientHandle != null)
			return;

		clientHandle = new AioClientHandler(Ch01Const.DEFAULT_SERVER_IP, Ch01Const.DEFAULT_PORT);

	}

	private static boolean sendMsg(String msg) {
		if ("q".equals(msg))
			return false;

		System.out.println(msg);

		return true;
	}

	public static void main(String[] args) {
		AioClient.start();
		System.out.println("请输入请求消息：");
		Scanner scanner = new Scanner(System.in);
		while (AioClient.sendMsg(scanner.nextLine()))
			;
	}

}
