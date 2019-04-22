package com.zj.study.netty.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", 8899));
		socketChannel.configureBlocking(true);

		String fileName = "/home/zj/imgs/minix.iso";

		FileChannel fileChannel = new FileInputStream(fileName).getChannel();
		long StartTime = System.currentTimeMillis();
		long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

		System.out.println(String.format("发送字节数:%d,耗时%d", transferCount, (System.currentTimeMillis() - StartTime)));
		fileChannel.close();
	}

}
