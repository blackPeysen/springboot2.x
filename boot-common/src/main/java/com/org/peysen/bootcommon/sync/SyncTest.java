package com.org.peysen.bootcommon.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/15
 * @Desc : 使用jol-core包打印对象的对象头信息
 */
public class SyncTest {

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }

    static class Demo{
        private int x;
        private boolean b;
    }
}
