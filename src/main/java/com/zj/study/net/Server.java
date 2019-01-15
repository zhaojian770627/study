package com.zj.study.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	volatile static boolean stop = false;

	public static void main(String[] args) throws IOException {
		ServerSocket ssock = new ServerSocket(1234);
		System.out.println("Listening");
		while (!stop) {
			Socket sock = ssock.accept();
			sock.setTcpNoDelay(true);
			ServerHandler fh = new ServerHandler(sock);
			fh.start();
		}
	}

}
