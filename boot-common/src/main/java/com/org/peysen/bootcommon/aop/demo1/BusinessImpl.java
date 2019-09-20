package com.org.peysen.bootcommon.aop.demo1;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/10 10:53
 */
public class BusinessImpl implements IBusiness, IBusiness2 {

    @Override
    public boolean doSomeThing() {
        System.out.println("执行业务逻辑");
        return true;
    }

    @Override
    public boolean doSomeThing2() {
        System.out.println("执行业务逻辑2");
        return false;
    }
}
