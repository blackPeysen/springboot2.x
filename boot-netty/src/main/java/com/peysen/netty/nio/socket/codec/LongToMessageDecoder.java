package com.peysen.netty.nio.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/28_07:52
 * @Desc: 解码器
 */
public class LongToMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("LongToMessageDecoder 解码器被调用。。。");
        list.add(byteBuf.readLong());

    }
}
