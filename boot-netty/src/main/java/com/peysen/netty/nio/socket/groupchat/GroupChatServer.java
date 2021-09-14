package com.peysen.netty.nio.socket.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/13_07:30
 * @Desc:
 */
public class GroupChatServer {
    private static final int port = 6667;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public GroupChatServer() {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
            this.serverSocketChannel.configureBlocking(false);

            this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        try{
            while(true){
                int count = this.selector.select(2000);
                if (count <= 0){
                    System.out.println("等待2s，暂未事件发生。。。");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("事件数量：" + selectionKeys.size());
                handleEvent(this.serverSocketChannel, this.selector, selectionKeys);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }

    }


    /**
     * 根据不同事件进行处理
     *
     * @param serverSocketChannel
     * @param selector
     * @param selectionKeys
     * @throws IOException
     */
    private void handleEvent(ServerSocketChannel serverSocketChannel, Selector selector, Set<SelectionKey> selectionKeys) throws IOException {
        Iterator<SelectionKey> iterator = selectionKeys.iterator();

        while (iterator.hasNext()){
            SelectionKey selectionKey = iterator.next();

            // 手动从集合中将当前的selectionKey移除，防止该事件被重复处理
            iterator.remove();

            // 说明是客户端连接事件
            if (selectionKey.isAcceptable()){
                handleAccept(serverSocketChannel, selector);
            }

            // 说明是客户端可读事件
            if (selectionKey.isReadable()){
                handleRead(selectionKey);
            }
        }
    }

    private void handleAccept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        // 为该客户端生成一个SocketChannel,并设置为非堵塞
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        // 注册到selector上, 关注事件为OP_READ(可读事件)，同时给socketChannel绑定buffer
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

        System.out.println("客户端：" + socketChannel.getRemoteAddress() + " 上线了。。。");
    }

    private void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            System.out.println("channle : " + channel.hashCode());
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

            int read = 0;
            while(true) {
                byteBuffer.clear();
                read = channel.read(byteBuffer);

                if (read == -1) {
                    System.out.println("客户端断开。。。。");
                    selectionKey.cancel();
                    channel.close();
                    break;
                }
                if (read == 0) {
                    System.out.println("客户端没有数据写入。。");
                    break;
                }
                String msg = new String(byteBuffer.array(), 0, read);
                System.out.println("从客户端中获取数据:" + msg);
                sendInfoToOtherClient(msg, channel);
            }
        } catch (IOException e){
            System.out.println(channel.getRemoteAddress() + "客户端下线。。。。");
            // 取消注册，关闭通道
            selectionKey.cancel();
            channel.close();
        }
    }

    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器信息转发中。。。");
        Set<SelectionKey> keys = this.selector.keys();

        for (SelectionKey key : keys){
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self){
                SocketChannel socketChannel = (SocketChannel)channel;

                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));

                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();

        groupChatServer.listen();
    }
}
