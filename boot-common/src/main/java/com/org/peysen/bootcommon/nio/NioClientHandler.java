package com.org.peysen.bootcommon.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 专门负责来接受服务器端的响应数据
 * Created by mengmeng.Pei
 * 2019/9/19 14:55
 */
public class NioClientHandler implements Runnable {
    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        int readyChannels = 0;
        Set<SelectionKey> selectionKeys = null;
        try {
            while (true){
                readyChannels = selector.select();

                System.out.println("可用channel数量:" + readyChannels);
                if (readyChannels == 0) continue;

                selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;

                while(iterator.hasNext()){
                    selectionKey = iterator.next();
                    //移除Set中的SelectionKey
                    iterator.remove();

                    //7.根据就绪状态，调用对应方法处理的业务逻辑
                    //可读事件
                    if (selectionKey.isReadable()){
                        this.readHeadler(selectionKey,selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readHeadler(SelectionKey selectionKey, Selector selector) throws IOException {
        //从SelectionKey中获取到已经就绪的socketChannel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //循环读取服务器端响应信息
        String message = "";
        while (socketChannel.read(byteBuffer) > 0){
            //切换为buffer为读模式
            byteBuffer.flip();
            message += Charset.forName("UTF-8").decode(byteBuffer);
        }

        //将channel再次注册到Selector上，监听他的下一次可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

        //将服务器端响应信息打印到本地
        if (StringUtils.isNotBlank(message)){
            System.out.println("message:" + message);
        }
    }
}
