package com.peysen.gof23.behaviour.responsibilityChain;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 14:34
 * @Desc:
 */
public class DepartMentHandler extends AbstractHandler {


    public DepartMentHandler(String name) {
        super(name);
    }

    @Override
    public void process(PurchaseRequest purchaseRequest) {
        float price = purchaseRequest.getPrice();
        if (price > 0.0f && price <= 5000f){
            System.out.println("该采购订单：" + purchaseRequest.getName() + "被" + this.name + "处理。。。");
            return;
        }
        this.handler.process(purchaseRequest);
    }
}
