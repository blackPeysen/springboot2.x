package com.peysen.netty.nio.socket.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/17_07:49
 * @Desc:
 */
public class NettyHttpChannelHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        System.out.println("当前handler#hashcode:" + this.hashCode() + ", pipline#hashcode:" + channelHandlerContext.pipeline().hashCode());

        System.out.println("当前请求的类型:" + httpObject.getClass());

        if(httpObject instanceof HttpRequest){
            HttpRequest request = (HttpRequest) httpObject;

            // 可以根据url做资源拦截
            URI uri = new URI(request.uri());

            ByteBuf content = Unpooled.copiedBuffer("hello，我是服务器端。。", StandardCharsets.UTF_8);
            DefaultHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            channelHandlerContext.channel().writeAndFlush(httpResponse);
        }
    }
}
