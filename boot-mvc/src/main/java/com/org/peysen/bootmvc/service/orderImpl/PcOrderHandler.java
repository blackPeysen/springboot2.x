package com.org.peysen.bootmvc.service.orderImpl;

@com.org.peysen.bootmvc.annotation.OrderHandlerType(source = "pc", payMethod = "zfb")
public class PcOrderHandler implements com.org.peysen.bootmvc.service.OrderHandler {
    @Override
    public void handler(com.org.peysen.bootmvc.entity.Order order) {
        System.out.println("处理pc端订单");
    }
}
