package com.peysen.gof23.behaviour.responsibilityChain;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 14:31
 * @Desc: 接受处理请求的接口
 */
public abstract class AbstractHandler {
    String name;
    AbstractHandler handler;

    public AbstractHandler(String name) {
        this.name = name;
    }

    abstract void process(PurchaseRequest purchaseRequest);

    public void setHandler(AbstractHandler handler) {
        this.handler = handler;
    }
}
