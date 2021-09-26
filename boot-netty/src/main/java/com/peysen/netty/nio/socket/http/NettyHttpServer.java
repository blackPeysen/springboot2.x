package com.peysen.netty.nio.socket.http;

import com.peysen.netty.nio.socket.netty.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/17_07:46
 * @Desc:
 */
public class NettyHttpServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try{
            serverBootstrap.group(bossLoopGroup, workLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyHttpChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1",6668).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("服务器启动6668端口成功，等待客户端连接。。。");
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }
    }

}
