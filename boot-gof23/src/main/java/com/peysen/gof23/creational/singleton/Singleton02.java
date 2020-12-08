package com.peysen.gof23.creational.singleton;

import static java.util.Objects.isNull;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:11
 * @Desc: 懒汉式+线程不安全
 *      1、将构造器私有化
 *      2、声明一个类变量，但不完成初始化操作
 *      3、声明一个类方法，在方法中判断类变量是否被实例化，若没有，则进行初始化。
 *
 *  优点：
 *      实现了懒加载，不浪费内存
 *  缺点：
 *      在多线程环境下，是不安全的，无法控制单例。
 */
public class Singleton02 {
    private static Singleton02 instance = null;

    private Singleton02(){}

    public static Singleton02 getInstance(){
        if (isNull(instance)){
            instance = new Singleton02();
        }

        return instance;
    }
}
