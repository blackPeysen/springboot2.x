package com.peysen.gof23.behaviour.responsibilityChain;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 14:40
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, "采购座椅订单", 7000f);

        AbstractHandler departMentHandler = new DepartMentHandler("系部张主任");
        AbstractHandler courtHandler = new CourtHandler("院部王主任");
        AbstractHandler schoolHandler = new SchoolHandler("校部裴主任");

        departMentHandler.setHandler(courtHandler);
        courtHandler.setHandler(schoolHandler);
        schoolHandler.setHandler(departMentHandler);

        departMentHandler.process(purchaseRequest);
        courtHandler.process(purchaseRequest);
        schoolHandler.process(purchaseRequest);

    }
}
