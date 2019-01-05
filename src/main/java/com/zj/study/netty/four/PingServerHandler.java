package com.zj.study.netty.four;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class PingServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;

			String eventType = null;

			switch (event.state()) {
			case READER_IDLE:
				eventType = "read idle";
				break;
			case WRITER_IDLE:
				eventType = "write idle";
				break;
			case ALL_IDLE:
				eventType = "all idle";
				break;
			}

			System.out.println(ctx.channel().remoteAddress() + " timeout event:" + eventType);
			ctx.channel().close();
		}

	}

}
