package com.zj.study.netty.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class OldIOClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8899);
		String fileName = "/home/zj/imgs/minix.iso";
		InputStream inputStream = new FileInputStream(fileName);
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

		byte[] buffer = new byte[4096];
		long readCount;
		long total = 0;

		long StartTime = System.currentTimeMillis();

		while ((readCount = inputStream.read(buffer)) >= 0) {
			total += readCount;
			dataOutputStream.write(buffer);
		}
		System.out.println(String.format("发送字节数:%d,耗时%d", total, (System.currentTimeMillis() - StartTime)));

		dataOutputStream.close();
		socket.close();
		inputStream.close();
	}

}
