package com.zj.study.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

	public static void main(String[] args) {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);

			Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

			while (true) {
				selector.select();
				Set<SelectionKey> keySet = selector.selectedKeys();
				for (SelectionKey selectionKey : keySet) {
					if (selectionKey.isConnectable()) {
						SocketChannel client = (SocketChannel) selectionKey.channel();
						if (client.isConnectionPending()) {
							client.finishConnect();

							ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
							writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
							writeBuffer.flip();
							client.write(writeBuffer);

							ExecutorService executorService = Executors
									.newSingleThreadExecutor(Executors.defaultThreadFactory());

							executorService.submit(new Runnable() {

								@Override
								public void run() {
									while (true) {
										try {
											writeBuffer.clear();
											InputStreamReader input = new InputStreamReader(System.in);
											BufferedReader br = new BufferedReader(input);
											String sendMessage = br.readLine();
											writeBuffer.put(sendMessage.getBytes());
											writeBuffer.flip();
											client.write(writeBuffer);
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									}
								}
							});
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
