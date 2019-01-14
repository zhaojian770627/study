package com.zj.study.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServerHandler extends Thread {

	private Socket sock;
	private BufferedOutputStream bufferedOutput;
	private BufferedInputStream bufferedInput;

	private ByteBuffer bb = ByteBuffer.allocate(1024);

	public ServerHandler(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			bufferedOutput = new BufferedOutputStream(sock.getOutputStream());
			bufferedInput = new BufferedInputStream(sock.getInputStream());

			DataInput dataIn = new DataInputStream(bufferedInput);
			DataOutput dataOut = new DataOutputStream(bufferedOutput);

			while (true) {
				writeStr(dataOut, "hello");
				String str = readStr(dataIn);
				System.out.println("Thread" + Thread.currentThread().getId() + "-" + str);
			}
		} catch (IOException e) {
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

	private String readStr(DataInput dataIn) throws IOException, UnsupportedEncodingException {
		int len = dataIn.readInt();
		byte b[] = new byte[len];
		dataIn.readFully(b);
		String str = new String(b, "UTF8");
		return str;
	}

	public void writeStr(DataOutput out, String str) throws IOException {
		ByteBuffer bb = stringToByteBuffer(str);
		out.writeInt(bb.remaining());
		out.write(bb.array(), bb.position(), bb.limit());
	}

	final private ByteBuffer stringToByteBuffer(CharSequence s) {
		bb.clear();
		final int len = s.length();
		for (int i = 0; i < len; i++) {
			if (bb.remaining() < 3) {
				ByteBuffer n = ByteBuffer.allocate(bb.capacity() << 1);
				bb.flip();
				n.put(bb);
				bb = n;
			}
			char c = s.charAt(i);
			if (c < 0x80) {
				bb.put((byte) c);
			} else if (c < 0x800) {
				bb.put((byte) (0xc0 | (c >> 6)));
				bb.put((byte) (0x80 | (c & 0x3f)));
			} else {
				bb.put((byte) (0xe0 | (c >> 12)));
				bb.put((byte) (0x80 | ((c >> 6) & 0x3f)));
				bb.put((byte) (0x80 | (c & 0x3f)));
			}
		}
		bb.flip();
		return bb;
	}

}
