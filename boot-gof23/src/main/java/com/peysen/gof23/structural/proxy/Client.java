package com.peysen.gof23.structural.proxy;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 9:53
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("===========");
        // 图像不需要从磁盘加载
        image.display();
    }
}
