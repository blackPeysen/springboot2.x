package com.org.peysen.bootcommon.reflect;

/**
 * @Description: 动态加载类
 *      new 关键字：创建对象，是静态加载类，在编译时刻就需要加载所有的可能使用到的类
 *      通过Class.forName()动态加载类，可以解决以上问题
 * Created by mengmeng.Pei
 * 2019/9/20 13:56
 */
public class ClassDemo2 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<Integer> intClass = int.class;
        Class<String> stringClass = String.class;

        System.out.println(intClass.getName());
        System.out.println(stringClass.getName());
        System.out.println(stringClass.getSimpleName());

    }

}
