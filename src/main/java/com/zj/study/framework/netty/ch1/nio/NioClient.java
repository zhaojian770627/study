package com.zj.study.framework.netty.ch1.nio;

import java.util.Scanner;

import com.zj.study.framework.netty.ch1.Ch01Const;

public class NioClient {
	{
	private static NioClientHandle nioClientHandle;

	private static void start() {
		if (nioClientHandle != null)
			nioClientHandle.stop();
		nioClientHandle = new NioClientHandle(Ch01Const.DEFAULT_SERVER_IP, Ch01Const.DEFAULT_PORT);
		new Thread(nioClientHandle, "Client").start();
	}

	public static void main(String[] args) {
		start();
		Scanner scanner = new Scanner(System.in);
		while (NioClient.sendMsg(scanner.next()))
			;
	}

	private static boolean sendMsg(String msg) {
		nioClientHandle.sendMsg(msg);
		return false;
	}

}
