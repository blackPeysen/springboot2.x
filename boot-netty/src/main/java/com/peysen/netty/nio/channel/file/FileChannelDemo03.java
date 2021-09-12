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
 * @Desc: 使用{@link Channel#transferFrom()} 进行文件拷贝
 */
public class FileChannelDemo03 {
    public static void main(String[] args) throws IOException {

        // 创建文件输入，输出流
        File filePath = new File("/Users/peysen/tmp/裴萌萌.JPG");
        FileInputStream inputStream = new FileInputStream(filePath);
        FileChannel inputChannel = inputStream.getChannel();

        File filePath1 = new File("/Users/peysen/tmp/裴萌萌copy.JPG");
        FileOutputStream outputStream = new FileOutputStream(filePath1);
        FileChannel outputChannel = outputStream.getChannel();

        /**
         * 直接使用transferFrom进行数据拷贝
         */
        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        /**
         * 关闭相关的流
         */
        inputStream.close();
        outputStream.close();
    }

}
