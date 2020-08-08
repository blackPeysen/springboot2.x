package com.org.peysen.bootcommon.thread.gcUtils;

import java.io.IOException;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/29
 * @Desc : jstat命令测试
 */
public class JstatDemo {

    // -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:-PrintGCDetails -verbose:gc
    public static void main(String[] args) throws IOException {
        final int _1MB = 1024 * 1024;
        byte[] b1 = new byte[2 * _1MB];
        System.out.println("1....");
        System.in.read();

        byte[] b2 = new byte[2 * _1MB];
        System.out.println("2....");
        System.in.read();

        byte[] b3 = new byte[2 * _1MB];
        System.out.println("3....");
        System.in.read();
    }

}
