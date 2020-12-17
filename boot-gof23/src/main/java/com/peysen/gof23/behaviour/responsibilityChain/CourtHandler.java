package com.peysen.gof23.behaviour.responsibilityChain;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 14:39
 * @Desc:
 */
public class CourtHandler extends AbstractHandler {
    public CourtHandler(String name) {
        super(name);
    }

    @Override
    public void process(PurchaseRequest purchaseRequest) {
        float price = purchaseRequest.getPrice();
        if (price > 5000f && price <= 10000f){
            System.out.println("该采购订单：" + purchaseRequest.getName() + "被" + this.name + "处理。。。");
            return;
        }
        this.handler.process(purchaseRequest);
    }
}
