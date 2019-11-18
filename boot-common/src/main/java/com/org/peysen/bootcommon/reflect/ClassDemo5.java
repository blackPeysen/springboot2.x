package com.org.peysen.bootcommon.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Description: 通过反射看清泛型的本质
 * Created by mengmeng.Pei
 * 2019/9/20 14:46
 */
public class ClassDemo5 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        ArrayList<String> list1 = new ArrayList<>();

        Class<? extends ArrayList> c1 = list.getClass();
        Class<? extends ArrayList> c2 = list1.getClass();

        System.out.println(c1 == c2);

        /**
         * 反射的操作都是编译之后的操作
         * c1==c2结果返回true：说明编译之后集合的泛型是去泛型化的。
         * Java中集合的泛型，是防止错误输入的，只在编译阶段有效，绕过编译就无效。
         * 验证：可以同方法的反射来操作，绕过编译
         */
        try {
            Method add = c2.getMethod("add", Object.class);
            add.invoke(list1,100);//绕过编译就绕过了泛型
            System.out.println(list1.size());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
