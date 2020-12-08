package com.peysen.gof23.creational.singleton;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:17
 * @Desc: 懒汉式+线程安全
 *      1、将构造函数私有化
 *      2、声明一个静态内部类，在静态内部类中声明一个类实例变量
 *
 *   优点：
 *      实现了懒加载+线程安全+效率高
 *      因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance
 *   
 */
public class Singleton05 {
    private static class Singleton05Inner{
        private static final Singleton05 instance = new Singleton05();
    }

    private Singleton05(){}

    public static Singleton05 getInstance(){
        return Singleton05Inner.instance;
    }
}
