package com.peysen.netty.nio.socket.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/17_07:48
 * @Desc:
 */
public class NettyHttpChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HttpServerCodec());

        socketChannel.pipeline().addLast(new NettyHttpChannelHandler());
    }
}
