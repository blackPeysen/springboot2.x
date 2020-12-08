package com.peysen.gof23.creational.singleton;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:17
 * @Desc: 懒汉式+线程安全
 *   使用枚举实现单例模式
 *   更简洁，自动支持序列化机制，绝对防止多次实例化。
 *      这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，
 *      而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
 */
public enum  Singleton06 {
    INSTANCE,
    ;

}
