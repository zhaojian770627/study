package com.zj.study.framework.netty.ch1.nio;

import java.io.IOException;

import com.zj.study.framework.netty.ch1.Ch01Const;

public class NioServer {
	private static NioServerHandle nioServerHandle;

	public static void start() throws IOException {
		if (nioServerHandle != null)
			nioServerHandle.stop();
		nioServerHandle = new NioServerHandle(Ch01Const.DEFAULT_PORT);
		new Thread(nioServerHandle, "Server").start();
	}

	public static void main(String[] args) throws IOException {
		start();
	}
}
