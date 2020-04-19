package com.zj.study.framework.netty.ch1.nio;

import java.io.IOException;
import java.util.Scanner;

import com.zj.study.framework.netty.ch1.Ch01Const;

public class NioClient {
	private static NioClientHandle nioClientHandle;

	private static void start() throws IOException {
		if (nioClientHandle != null)
			nioClientHandle.stop();
		nioClientHandle = new NioClientHandle(Ch01Const.DEFAULT_SERVER_IP, Ch01Const.DEFAULT_PORT);
		new Thread(nioClientHandle, "Client").start();
	}

	public static void main(String[] args) throws Exception {
		start();
		Scanner scanner = new Scanner(System.in);
		while (NioClient.sendMsg(scanner.next()))
			;
	}

	private static boolean sendMsg(String msg) throws Exception {
		nioClientHandle.sendMsg(msg);
		return true;
	}

}
