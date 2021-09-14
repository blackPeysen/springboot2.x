package com.peysen.netty.nio.socket.zreocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/14_07:58
 * @Desc:
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(7000);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(socketAddress);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

            int readCount = 0;
            FileChannel channel;
            while(-1 != (readCount = socketChannel.read(byteBuffer))){
                byteBuffer.rewind();
            }
        }
    }
}
