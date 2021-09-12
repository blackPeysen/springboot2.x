package com.peysen.netty.nio.channel.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/11_18:48
 * @Desc: 使用同一个Buffer 对文件进行拷贝操作
 */
public class FileChannelDemo02 {
    public static void main(String[] args) throws IOException {
        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        // 创建文件输入，输出流
        File filePath = new File("/Users/peysen/tmp/fileChannle.txt");
        FileInputStream inputStream = new FileInputStream(filePath);
        FileChannel inputChannel = inputStream.getChannel();

        File filePath1 = new File("/Users/peysen/tmp/fileChannle1.txt");
        FileOutputStream outputStream = new FileOutputStream(filePath1);
        FileChannel outputChannel = outputStream.getChannel();

        /**
         * 从channel中读取数据， 写入到buffer中
         */
        int read = 0;
        while((read = inputChannel.read(byteBuffer)) != -1){
            // 这里一定先要将buffer清空，防止文件过大，一次读取，buffer容量不大，直接爆了
            byteBuffer.clear();

            /**
             *  buffer 翻转，从buffer中读取数据，写入到另一个文件中
             *      1.先将buffer进行翻转
             *      2.写入channel中
             */
            byteBuffer.flip();
            System.out.println("read after:" + new String(byteBuffer.array(), "UTF-8"));
            outputChannel.write(byteBuffer);
        }

        /**
         * 关闭相关的流
         */
        inputStream.close();
        outputStream.close();
    }

}
