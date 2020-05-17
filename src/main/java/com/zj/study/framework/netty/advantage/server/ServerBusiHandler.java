package com.zj.study.framework.netty.advantage.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zj.study.framework.netty.advantage.vo.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Mark老师 享学课堂 https://enjoy.ke.qq.com 往期课程和VIP课程咨询 依娜老师 QQ：2133576719
 *         类说明：业务处理类
 */

public class ServerBusiHandler extends SimpleChannelInboundHandler<Message> {
	private static final Log LOG = LogFactory.getLog(ServerBusiHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		LOG.info(msg);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LOG.info(ctx.channel().remoteAddress() + " 主动断开了连接!");
	}

}
