package com.org.peysen.bootmvc.service;

public class OrderServiceTest extends com.org.peysen.bootmvc.BootMvcApplicationTests {
    @org.springframework.beans.factory.annotation.Autowired
    private OrderService orderService;

    @org.junit.Test
    public void orderservice() {
        com.org.peysen.bootmvc.entity.Order order = new com.org.peysen.bootmvc.entity.Order();
        order.setSource("pc");
        order.setPayMethod("wx");
        orderService.orderservice(order);
    }

    @org.junit.Test
    public void orderservice1() {
        com.org.peysen.bootmvc.entity.Order order = new com.org.peysen.bootmvc.entity.Order();
        order.setSource("mobile");
        order.setPayMethod("wx");
        orderService.orderservice1(order);
    }
}