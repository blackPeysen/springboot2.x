package com.org.peysen.bootcommon.reflect;

/**
 * @Description: 获取Class实例对象的三种方式
 * Created by mengmeng.Pei
 * 2019/9/20 13:47
 */
public class ClassDemo1 {

    public static void main(String[] args) {
        //Foo的实例对象如何表示?
        Foo foo = new Foo();

        //Foo这个类 本身也是一个实例对象，Class类的实例对象，如何表示?

        //1、任何一个类都有一个隐含的静态成员变量class
        Class<Foo> c1 = Foo.class;

        //2、已经知道该类的对象通过getClass方法
        Class<? extends Foo> c2 = foo.getClass();

        System.out.println(c1 == c2);
        /**
         * c1，c2表示了Foo类的类类型（class type）
         * 不管c1，c2都代表了Foo类的类类型，一个类只可能是Class类的一个实例对象
         */

        Class c3 = null;
        try {
            //3、通过Class.forName获取Class实例对象
            c3 = Class.forName("com.org.peysen.bootcommon.reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c3 == c2);

        //获取类的类类型创建该类的实例对象
        try {
            //需要有无参的构造函数
            Foo foo1 = c1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Foo{}
