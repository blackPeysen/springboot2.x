package com.peysen.gof23.creational.singleton;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:08
 * @Desc: 饿汉式
 *      1、将构造器私有化
 *      2、声明一个类变量，并完成初始化操作
 *      3、声明一个类方法，提供类变量的访问方法
 *
 *  优点：没有加锁，执行效率会提高。
 *  缺点：类加载时就初始化，浪费内存。
 *
 */
public class Singleton01 {
    private static final Singleton01 instance = new Singleton01();

    private Singleton01(){ }

    public static Singleton01 getInstance(){
        return instance;
    }
}
