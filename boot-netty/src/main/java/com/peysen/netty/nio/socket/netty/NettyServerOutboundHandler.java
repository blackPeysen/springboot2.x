package com.peysen.netty.nio.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

/**
 * Author: peimengmeng
 * Date: 2021/9/15 14:11
 * Desc:
 */
public class NettyServerOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String content = byteBuf.toString(StandardCharsets.UTF_8);
        System.out.println("NettyServerOutboundHandler write-------------" + content);
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyServerOutboundHandler flush-------------");
        super.flush(ctx);
    }
}
