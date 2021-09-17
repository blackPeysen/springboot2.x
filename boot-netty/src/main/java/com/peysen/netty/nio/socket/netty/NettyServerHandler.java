package com.peysen.netty.nio.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * Author: peimengmeng
 * Date: 2021/9/15 14:11
 * Desc:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(Thread.currentThread().getName());

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("pmmmm");
            }
        });

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("获取到客户端发送的消息:" + byteBuf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("服务端收到客户端的请求了，请继续。。。", StandardCharsets.UTF_8));
    }
}
