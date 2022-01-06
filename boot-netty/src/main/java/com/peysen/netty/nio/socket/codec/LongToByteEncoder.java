package com.peysen.netty.nio.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/28_07:52
 * @Desc: 编码器
 */
public class LongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
        System.out.println("MessageToLongEncoder 编码器被调用:" + aLong);
        byteBuf.writeLong(aLong);
    }
}
