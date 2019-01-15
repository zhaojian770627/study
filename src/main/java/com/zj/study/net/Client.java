package com.zj.study.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = null;
		try {
			Util util = new Util();
			sock = new Socket();
			SocketAddress sockaddr = new InetSocketAddress("localhost", 1234);
			sock.connect(sockaddr, 2000);
			sock.setTcpNoDelay(true);
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(sock.getOutputStream());
			BufferedInputStream bufferedInput = new BufferedInputStream(sock.getInputStream());
			DataInput dataIn = new DataInputStream(bufferedInput);
			DataOutputStream dataOut = new DataOutputStream(bufferedOutput);

			String hellostr = util.readStr(dataIn);
			System.out.println(hellostr);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			for (;;) {
				String readStr = br.readLine();
				if ("quit".equals(readStr))
					break;

				if (readStr != null && readStr.length() > 0) {
					util.writeStr(dataOut, readStr);
					bufferedOutput.flush();
				}
			}
		} finally {
			System.out.println("client not link");
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
