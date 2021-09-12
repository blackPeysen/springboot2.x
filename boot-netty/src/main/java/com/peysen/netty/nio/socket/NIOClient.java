package com.peysen.netty.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/12_16:53
 * @Desc:
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 6666);

        if (!socketChannel.connect(socketAddress)){
            // 连接失败，客户端也不会堵塞，还可以干其他事
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接服务器需要时间，客户端也不会堵塞，可以干其他事。。");
            }
        }

        // 连接成功，就可以发送数据
        String data = "hello, world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));

        // 将buffer中的数据写入channle中
        socketChannel.write(byteBuffer);

        System.in.read();

//        socketChannel.close();
    }
}
