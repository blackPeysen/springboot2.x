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
public class NettyServerInboundHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(Thread.currentThread().getName() + " NettyServerInboundHandler channelRead222222222222222222");

//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println("pmmmm");
//            }
//        });

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("获取到客户端发送的消息:" + byteBuf.toString(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello world", StandardCharsets.UTF_8));

        System.out.println("服务器发送消息成功++++++++++++++++++");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyServerInboundHandler channelReadComplete++++++++++++++++++++");
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("服务端收到客户端的请求了，请继续。。。", StandardCharsets.UTF_8));
    }
}
