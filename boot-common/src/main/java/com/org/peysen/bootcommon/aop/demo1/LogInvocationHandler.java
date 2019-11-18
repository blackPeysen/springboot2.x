package com.org.peysen.bootcommon.aop.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: 打印日志的切面
 * Created by mengmeng.Pei
 * 2019/9/10 10:45
 */

public  class LogInvocationHandler implements InvocationHandler {
    private Object target; //目标对象

    LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行原有逻辑
        Object rev = method.invoke(target, args);

        //执行织入的日志，你可以控制哪些方法执行切入逻辑
        if (method.getName().equals("doSomeThing2")) {
            System.out.println("记录日志");
        }
        return rev;
    }

    public static void main(String[] args) {
        Integer i = new Integer(1);
        if (i.equals(1))
            i = null;
        Double d = new Double(2.0);
        Object o = i!=null ? i : d;
        System.out.println(o);
    }
}
