package com.org.peysen.bootcommon.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 方法反射调用
 * Created by mengmeng.Pei
 * 2019/9/20 14:33
 */
public class ClassDemo4 {

    public static void main(String[] args) {
        MethodDemo methodDemo = new MethodDemo();
        Class<? extends MethodDemo> demoClass = methodDemo.getClass();

        try {
            //获取方法:通过方法的名称及方法的参数列表获取Method对象
            Method method = null;
            Object returnValue = null;
            /**
             * 通过method对象进行反射操作
             * 如果方法有返回值，则返回具体的返回值；若没有则返回null
             */
            method = demoClass.getMethod("print", new Class[]{int.class, int.class});
            returnValue = method.invoke(methodDemo, 1, 2);
            System.out.println("returnValue:" + returnValue);

            method = demoClass.getMethod("print", new Class[]{String.class, String.class});
            returnValue = method.invoke(methodDemo, "dsaf", "dsaf");
            System.out.println("returnValue:" + returnValue);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

class MethodDemo {

    public void print(int i,int b){
        System.out.println("print int :"+ (i+b));
    }

    public void print(String a,String b){
        System.out.println("print string :" + a.toLowerCase() + b.toLowerCase());
    }
}
