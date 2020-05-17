package com.zj.study.framework.netty.advantage.kryocodec;

import com.zj.study.framework.netty.advantage.vo.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：序列化的Handler
 */
public class KryoEncoder  extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message,
                          ByteBuf out) throws Exception {
        KryoSerializer.serialize(message, out);
        ctx.flush();
    }
}
