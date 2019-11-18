package com.org.peysen.bootcommon.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: NIO服务端
 * Created by mengmeng.Pei
 * 2019/9/19 14:17
 */
public class NioServer {

    /**
     * 启动
     */
    public void start() throws IOException {
        //1.创建Selector
        Selector selector = Selector.open();

        //2.通过ServerSocketChannel创建channle通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        //3.为channel通道绑定监听端口
        socketChannel.bind(new InetSocketAddress(8080));

        //4.设置channel为为阻塞模式
        socketChannel.configureBlocking(false);

        //5.将channle注册到selector上，监听连接事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        //6.循环等到新接入的连接
        int readyChannels = 0;
        Set<SelectionKey> selectionKeys = null;
        while (true){
            //获取可用的channel数量
            readyChannels = selector.select();

            System.out.println("可用channel数量：" + readyChannels);
            /**
             * TODO:WHY?
             */
            if (readyChannels == 0) continue;

            //获取可用的channel集合
            selectionKeys = selector.selectedKeys();

            //遍历selectionKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey selectionKey = null;

            while(iterator.hasNext()){
                selectionKey = iterator.next();

                //移除Set中的SelectionKey
                iterator.remove();

                //7.根据就绪状态，调用对应方法处理的业务逻辑
                //接入事件
                if (selectionKey.isAcceptable()){
                    this.acceptHandler(socketChannel,selector);
                }

                //可读事件
                if (selectionKey.isReadable()){
                    this.readHeadler(selectionKey,selector);
                }
            }
        }
    }

    /**
     * 接入事件处理器
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector) throws IOException {
        //创建SocketChannel,用于与客户端进行通信
        SocketChannel socketChannel = serverSocketChannel.accept();

        //将SocketChannel设置为非阻塞工作模式
        socketChannel.configureBlocking(false);

        //将SocketChannel注册到selector上，监听可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        //回复客户端提示信息
        String msg= "你与聊天室其他人都不是朋友关系，请注意隐私";
        socketChannel.write(Charset.forName("UTF-8").encode(msg));
    }

    /**
     * 可读事件处理器
     * @param selectionKey
     * @param selector
     * @throws IOException
     */
    private void readHeadler(SelectionKey selectionKey,Selector selector) throws IOException {
        //从SelectionKey中获取到已经就绪的socketChannel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //循环读取客户端请求信息
        String message = "";
        while (socketChannel.read(byteBuffer) > 0){
            //切换为buffer为读模式
            byteBuffer.flip();
            message += Charset.forName("UTF-8").decode(byteBuffer);
        }

        //将channel再次注册到Selector上，监听他的下一次可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        //将客户端发送的请求信息，广播给其他客户端
        if (StringUtils.isNotBlank(message)){
            System.out.println("message:" + message);
            broadCast(selector,socketChannel,message);
        }
    }


    private void broadCast(Selector selector,SocketChannel sourceChannel,String message){
        //获取到所有已接入的客户端channel
        Set<SelectionKey> selectionKeys = null;
        if (selector != null){
            selectionKeys = selector.keys();

            //循环向所有channel广播信息
            selectionKeys.forEach(selectionKey -> {
                Channel targetChannle = selectionKey.channel();

                if (targetChannle instanceof  SocketChannel
                    && targetChannle != sourceChannel){
                    try {
                        //将信息发送到targetChannle客户端
                        ((SocketChannel) targetChannle).write(Charset.forName("UTF-8").encode(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void main(String[] args) throws IOException {

        NioServer nioServer = new NioServer();
        nioServer.start();
    }

}
