/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:31
 * @Desc: 原型设计模式
 *  完成对象从内部的拷贝工作，无需使用构造器|set方法进行属性复制。
 *
 *  实现方式有两种：
 *      1、实现Serializable、Cloneable接口，重写父类的clone()方法。
 *      2、通过序列化实现
 *
 *  浅拷贝：
 *      当类成员属性中有引用类型，只重写父类的clone()时，会导致所有的副本都共用一个引用类型地址，修改一处，全部都被修改。
 *  解决方案：
 *      深拷贝：将类成员属性中的引用类型也实现Cloneable接口，嵌套实现，直到所有的成员都是值类型
 *
 */
package com.peysen.gof23.creational.prototype;
