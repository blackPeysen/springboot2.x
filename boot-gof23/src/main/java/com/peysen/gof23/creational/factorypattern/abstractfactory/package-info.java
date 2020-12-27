/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:01
 * @Desc: 抽象工厂模式
 *      当产品子类过多时，对应的工厂数量剧增，可以将同一类型的产品集中在一个抽象工厂中.
 *      抽象工厂 = 简单工厂 + 工厂方法
 *
 *  概述：
 *      a、AbstractFactory：为抽象工厂，定义了创建一系列不同产品的接口方法
 *      b、ConcreteFactory：为抽象工厂的具体子类，实现创建具体产品对象的操作。
 *      c、AbstractProduct：为一类产品对象声明的一个接口
 *      d、ConcreteProduct：定义一个将被相应的具体工厂创建的产品对象，实现AbstractProduct接口
 *  协作：
 *      a、通常在运行时刻创建一个ConcreteFactory类的具体实例，这一具体工厂创建具有特定实现的产品对象，
 *          为创建不同的产品对象，客户应使用不同的具体工厂。
 *      b、AbstractFactory将产品对象的创建延迟到它的ConcreteFactory子类中实现。
 */
package com.peysen.gof23.creational.factorypattern.abstractfactory;
