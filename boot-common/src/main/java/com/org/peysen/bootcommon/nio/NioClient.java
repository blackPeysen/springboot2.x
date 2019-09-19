package com.org.peysen.bootcommon.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @Description: NIO客户端
 * Created by mengmeng.Pei
 * 2019/9/19 14:17
 */
public class NioClient {

    /**
     * 启动
     */
    public void  start(String nickName) throws IOException {
        //连接服务器
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 8080));

        //接收服务器数据
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        //向服务器端发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String message = scanner.nextLine();
            if (StringUtils.isNotBlank(message)){
                socketChannel.write(Charset.forName("UTF-8").encode(nickName + ":" + message));
            }
        }

    }


    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.start("AClient");
    }
}
