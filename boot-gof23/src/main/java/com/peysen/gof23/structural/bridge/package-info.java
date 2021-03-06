/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:38
 * @Desc: 桥接模式
 *  涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 *  该模式重点强调的是多维度的变化，在一个抽象类中组合了一个接口，可以使得抽象类和接口平行扩展功能，而不影响彼此。
 *  该模式的关键点是：
 *      主体类依赖抽象A: 可以是成员变量，可以是方法入参
 *      主体类具有多个不同的实现类
 *      抽象A具有多个不同的实现类
 *  最终的效果就是，主体类的实现类和抽象的实现类分别可以在两个维度上进行各自的变化。如果主体类依赖多个抽象，则维度进行增加，方便扩展。
 *
 */
package com.peysen.gof23.structural.bridge;
