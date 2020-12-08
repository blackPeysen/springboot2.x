package com.peysen.gof23.creational.singleton;

import static java.util.Objects.isNull;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:17
 * @Desc: 懒汉式+线程安全（双重锁检查：Double-checked-Locking）
 *      1、将构造函数私有化
 *      2、声明一个类变量，使用volatile修饰，但不完成初始化操作
 *      3、声明一个类方法，用双重锁检查。
 *
 *   优点：
 *      实现了懒加载+线程安全+效率高
 *
 */
public class Singleton04 {
    private static volatile Singleton04 instance = null;

    private Singleton04(){}

    /**
     * 为什么需要用volatile关键字？
     *      因为在instance = new Singleton04();时，并不是原子性的，可能出现分配了内存空间，但是还没将引用赋值给instance变量，
     *      使用volatile关键字保证指令不被重排。
     *
     * @return
     */
    public static Singleton04 getInstance(){
        if (isNull(instance)){
            synchronized (Singleton04.class){
                if (isNull(instance)){
                    instance = new Singleton04();
                }
            }
        }

        return instance;
    }
}
