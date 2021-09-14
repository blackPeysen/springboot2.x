package com.peysen.netty.nio.socket.zreocopy;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/14_07:58
 * @Desc:
 */
public class NewIOClient {

    public static void main(String[] args) {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost",7000);

        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(socketAddress);

            File file = new File("/Users/peysen/Downloads/gradle-7.2-bin.zip");
            FileChannel fileChannel = new FileInputStream(file).getChannel();

            long timeMillis = System.currentTimeMillis();
            /**
             * 底层使用0拷贝，提升性能
             */
            long transferTo = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

            System.out.println("共传输：" + transferTo +",消耗时间：" + (System.currentTimeMillis() - timeMillis));

            fileChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
