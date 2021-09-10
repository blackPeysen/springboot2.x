package com.peysen.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/10_07:45
 * @Desc: bio 模型下的服务端
 */
public class BIOServer {

    /**
     * 思想：使用一个线程池接受客户端的请求
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端。。。");

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }


    private static void handler(Socket socket){
        byte[] bytes = new byte[1024];

        try {
            InputStream inputStream = socket.getInputStream();

            while (inputStream.read(bytes) != -1){
                System.out.println("读到客户端数据：" + new String(bytes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(socket)){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
