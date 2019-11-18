package com.org.peysen.bootcommon.aop.demo1;

import java.lang.reflect.Proxy;

/**
 * @Description: 动态代理
 * Created by mengmeng.Pei
 * 2019/9/10 10:40
 */
public class DynamicProxy {

    public static void main(String[] args) {
        //需要代理的接口，被代理类实现的多个接口都必须在这里定义
        Class[] proxyInterface = new Class[]{ IBusiness.class, IBusiness2.class };

        //构建AOP的advice,需要传入业务类的实例
        LogInvocationHandler handler = new LogInvocationHandler(new BusinessImpl());

        //生成代理类的字节码加载器
        ClassLoader classLoader = DynamicProxy.class.getClassLoader();

        //织入器，织入代码并生成代理类
        IBusiness2 proxyBusiness = (IBusiness2) Proxy.newProxyInstance(classLoader, proxyInterface, handler);

        //使用代理类的实例来调用方法
        proxyBusiness.doSomeThing2();
        System.out.println("-----");
        ((IBusiness) proxyBusiness).doSomeThing();
    }
}
