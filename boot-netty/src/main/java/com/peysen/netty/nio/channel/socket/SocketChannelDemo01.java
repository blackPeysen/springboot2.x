package com.peysen.netty.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/12_15:56
 * @Desc:
 */
public class SocketChannelDemo01 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.bind(inetSocketAddress);

        ByteBuffer[] buffers = initBuffer();

        while(true){
            System.out.println("等待客户端连接。。。。。");
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 循环读取
            while(true){
                long read = socketChannel.read(buffers);
                Arrays.asList(buffers).stream().forEach(buffer ->{
                    System.out.println("pos:" + buffer.position() + ",lim:"+buffer.limit()
                    +", read:" + read);
                });

                // buffer翻转，准备往buffer中写回数据
                Arrays.asList(buffers).stream().forEach(buffer -> buffer.flip());

                // 可能出现BufferOverflowException，因为当某个buffer没有读取到数据，flip之后，lit就变成了0，此时put，就报错了
                Arrays.asList(buffers).stream().forEach(buffer -> buffer.put(new byte[]{97,98,99}));

                long write = socketChannel.write(buffers);
                Arrays.asList(buffers).stream().forEach(buffer ->{
                    System.out.println("pos:" + buffer.position() + ",lim:"+buffer.limit()
                            +", write:" + write);
                });

                Arrays.asList(buffers).stream().forEach(buffer -> buffer.clear());
            }
        }
    }

    private static ByteBuffer[] initBuffer(){
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(3);

        return new ByteBuffer[]{byteBuffer1, byteBuffer2};
    }
}
