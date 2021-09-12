package com.peysen.netty.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/12_16:53
 * @Desc:
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 配置ServerSocketChannel
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非堵塞模式
        serverSocketChannel.configureBlocking(false);

        // 生成一个选择器
        Selector selector = Selector.open();

        // 将serverSocketChannel 注册到Selector上，监听事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true){
            // 等待1s，看是否有客户端发起连接请求
            if (selector.select(1000) == 0){
                System.out.println("服务器端等待了一段时间，没有事件发生。。");
                continue;
            }

            // 说明此时有事件发生，获取selectionKey集合并处理, 可以通过selectionKey 反向获取到对应的channle
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            handleEvent(serverSocketChannel, selector, selectionKeys);
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
    private static void handleEvent(ServerSocketChannel serverSocketChannel, Selector selector, Set<SelectionKey> selectionKeys) throws IOException {
        System.out.println("事件数量：" + selectionKeys.size());
        Iterator<SelectionKey> iterator = selectionKeys.iterator();

        while (iterator.hasNext()){
            SelectionKey selectionKey = iterator.next();
            System.out.println("selectionKey：" + selectionKey.hashCode());

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

    private static void handleAccept(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        // 为该客户端生成一个SocketChannel,并设置为非堵塞
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        System.out.println("客户端生成成功：" + socketChannel.hashCode());

        // 注册到selector上, 关注事件为OP_READ(可读事件)，同时给socketChannel绑定buffer
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    private static void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        System.out.println("channle : " + channel.hashCode());
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

        try {
            if (channel.isOpen()){
                int read = 0;
                while((read = channel.read(byteBuffer)) > 0){
                    System.out.println("从客户端中获取数据：" + new String(byteBuffer.array(), 0, read));
                    byteBuffer.clear();
                }
            } else {
                System.out.println("客户端已经被断开。。。。");
                return;
            }
        } catch (Exception e){
            System.out.println("客户端断开。。。。");
            selectionKey.cancel();
            channel.close();
        }
    }

    public static String getString(ByteBuffer buffer) {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try
        {
            charset = StandardCharsets.UTF_8;
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
