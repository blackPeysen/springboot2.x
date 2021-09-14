package com.peysen.netty.nio.socket.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/13_07:51
 * @Desc:
 */
public class GroupChatClient {
    private static final String host = "127.0.0.1";
    private static final int port = 6667;

    private String username;
    private Selector selector;
    private SocketChannel socketChannel;

    public GroupChatClient() throws IOException {
        selector = Selector.open();

        socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);

        username = socketChannel.getLocalAddress().toString().substring(1);

        System.out.println(username + "IS OK..");
    }

    public void sendMsg(String message){
        message = username + "say :" + message;

        try{
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));

            socketChannel.write(byteBuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readMsg(){
        try{
            int select = selector.select(2000);

            if (select > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();

                    if(key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int read = socketChannel.read(byteBuffer);

                        String message = new String(byteBuffer.array(), 0, read);
                        System.out.println("客户端获取消息：" + message);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient();

        new Thread(){
            @Override
            public void run() {

                while(true){
                    groupChatClient.readMsg();

                    try{
                        Thread.currentThread().sleep(3000L);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String msg = scanner.nextLine();
            groupChatClient.sendMsg(msg);
        }
    }
}
