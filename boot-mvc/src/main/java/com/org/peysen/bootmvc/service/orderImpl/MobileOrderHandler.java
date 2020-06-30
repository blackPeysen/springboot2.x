package com.org.peysen.bootmvc.service.orderImpl;

@com.org.peysen.bootmvc.annotation.OrderHandlerType(source = "mobile", payMethod = "wx")
public class MobileOrderHandler implements com.org.peysen.bootmvc.service.OrderHandler {
    @Override
    public void handler(com.org.peysen.bootmvc.entity.Order order) {
        System.out.println("处理移动端订单");
    }
}
