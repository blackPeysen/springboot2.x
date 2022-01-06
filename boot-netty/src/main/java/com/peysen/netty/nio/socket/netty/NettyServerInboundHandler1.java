package com.peysen.netty.nio.socket.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Author: peimengmeng
 * Date: 2021/9/15 14:11
 * Desc:
 */
public class NettyServerInboundHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyServerInboundHandler1 --- channelRegistered");
        super.channelRegistered(ctx);
    }
}
