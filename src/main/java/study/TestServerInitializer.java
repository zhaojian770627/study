package study;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeliine = ch.pipeline();
		pipeliine.addLast("httpServerCodec", new HttpServerCodec());
		pipeliine.addLast("testHttpServerHandle", new TestHttpServerHandler());

	}

}
