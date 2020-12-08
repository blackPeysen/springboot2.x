package com.peysen.gof23.creational.singleton;

import static java.util.Objects.isNull;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:17
 * @Desc: 懒汉式+线程安全（效率低）
 *      1、将构造函数私有化
 *      2、声明一个类变量，但不完成初始化操作
 *      3、声明一个类方法，用synchronized修饰，在方法中判断类变量是否被实例化，若没有，则进行初始化。
 *
 *   优点：
 *      实现了懒加载+线程安全
 *   缺点：
 *      用synchronized修饰方法，效率低下
 */
public class Singleton03 {
    private static Singleton03 instance = null;

    private Singleton03(){}

    public static synchronized Singleton03 getInstance(){
        if (isNull(instance)){
            instance = new Singleton03();
        }

        return instance;
    }
}
