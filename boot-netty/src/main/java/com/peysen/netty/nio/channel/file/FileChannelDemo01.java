package com.peysen.netty.nio.channel.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/11_18:48
 * @Desc: 使用Channel 和 Buffer 对文件进行输入，输出操作
 */
public class FileChannelDemo01 {
    public static void main(String[] args) throws IOException {
        String str = "abc.";

        // 创建一个输出流
        String filePath = "/Users/peysen/tmp/fileChannle.txt";
        FileOutputStream outputStream = new FileOutputStream(new File(filePath));
        FileChannel channel = outputStream.getChannel();

        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将数据写入到buffer中
        byteBuffer.put(str.getBytes());

        /**
         *  将buffer写入到channel中
         *      1.先将buffer进行翻转
         *      2.写入channel中
         */
        byteBuffer.flip();

        channel.write(byteBuffer);
        outputStream.close();

        /**
         * 从channel中读取数据， 写入到buffer中
         */
        // 创建buffer
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        System.out.println("before:" + byteBuffer1.position());

        FileInputStream inputStream = new FileInputStream(new File(filePath));
        FileChannel fileChannel = inputStream.getChannel();

        fileChannel.read(byteBuffer1);
        System.out.println("read after:" + new String(byteBuffer.array(), "UTF-8"));
        inputStream.close();
    }

}
