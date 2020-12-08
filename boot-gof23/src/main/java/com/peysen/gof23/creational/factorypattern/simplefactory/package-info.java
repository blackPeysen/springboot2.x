/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 9:56
 * @Desc: 简单工厂模式
 *      定义一个工厂类，他可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类
 *      在简单工厂模式中用于被创建实例的方法通常为静态(static)方法,因此简单工厂模式又被成为静态工厂方法(Static Factory Method)
 *      需要什么，只需要传入一个正确的参数，就可以获取所需要的对象，而无需知道其实现过程
 *
 *   缺点：
 *      1、所有的子类都暴露在工厂方法中，由工厂根据参数进行if...else判断生成具体的子类；
 *      2、如果新增子类产品，还需要修改工厂方法，违背了OCP原则。
 */
package com.peysen.gof23.creational.factorypattern.simplefactory;
