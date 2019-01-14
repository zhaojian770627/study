package com.zj.study.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private volatile boolean stop = false;

	public static void main(String[] args) throws IOException {
		ServerSocket ssock = new ServerSocket(1234);
		while (true) {
			System.out.println("Listening");
			Socket sock = ssock.accept();
			ServerHandler fh = new ServerHandler(sock);
			fh.start();
		}
	}

}
