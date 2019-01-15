package com.zj.study.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Thread {

	private Socket sock;
	private BufferedOutputStream bufferedOutput;
	private BufferedInputStream bufferedInput;

	public ServerHandler(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			Util util = new Util();
			bufferedOutput = new BufferedOutputStream(sock.getOutputStream());
			bufferedInput = new BufferedInputStream(sock.getInputStream());

			DataInput dataIn = new DataInputStream(bufferedInput);
			DataOutputStream dataOut = new DataOutputStream(bufferedOutput);

			util.writeStr(dataOut, "hello");
			dataOut.flush();

			while (true) {
				String str = util.readStr(dataIn);
				System.out.println("Thread" + Thread.currentThread().getId() + "-" + str);
			}
		} catch (IOException e) {
			System.out.println("not link");
			if (sock != null && !sock.isClosed()) {
				// close the socket to make sure the
				// other side can see it being close
				try {
					sock.close();
				} catch (IOException ie) {
					// do nothing
				}
			}
		}
	}

}
