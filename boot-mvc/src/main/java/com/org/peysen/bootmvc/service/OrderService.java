package com.org.peysen.bootmvc.service;

@org.springframework.stereotype.Service
public class OrderService {

    private java.util.Map<String, OrderHandler> orderHandlerMap;

    private java.util.Map<com.org.peysen.bootmvc.annotation.OrderHandlerType, OrderHandler> handlerTypeOrderHandlerMap;

    @org.springframework.beans.factory.annotation.Autowired
    public void setOrderHandlerMap(java.util.List<com.org.peysen.bootmvc.service.OrderHandler> orderHandlers) {
        // 注入各种类型的订单处理类
        orderHandlerMap = orderHandlers.stream().collect(
                java.util.stream.Collectors.toMap(orderHandler -> org.springframework.core.annotation.AnnotationUtils.findAnnotation(orderHandler.getClass(), com.org.peysen.bootmvc.annotation.OrderHandlerType.class).source(),
                        v -> v, (v1, v2) -> v1));
    }

    @org.springframework.beans.factory.annotation.Autowired
    public void setHandlerTypeOrderHandlerMap(java.util.List<com.org.peysen.bootmvc.service.OrderHandler> orderHandlers) {
        // 注入各种类型的订单处理类
        handlerTypeOrderHandlerMap = orderHandlers.stream().collect(
                java.util.stream.Collectors.toMap(orderHandler -> org.springframework.core.annotation.AnnotationUtils.findAnnotation(orderHandler.getClass(), com.org.peysen.bootmvc.annotation.OrderHandlerType.class),
                                                  orderHandler -> orderHandler,
                                                  (v1, v2) -> v1));
    }

    public void orderservice(com.org.peysen.bootmvc.entity.Order order){
        com.org.peysen.bootmvc.service.OrderHandler orderHandler = orderHandlerMap.get(order.getSource());
        orderHandler.handler(order);
    }

    public void orderservice1(com.org.peysen.bootmvc.entity.Order order){
        // 通过订单来源确以及支付方式获取对应的handler
        com.org.peysen.bootmvc.annotation.OrderHandlerType orderHandlerType = new com.org.peysen.bootmvc.annotation.OrderHandlerTypeImpl(order.getSource(), order.getPayMethod());
        OrderHandler orderHandler = handlerTypeOrderHandlerMap.get(orderHandlerType);
        orderHandler.handler(order);
    }

    public static void main(String[] args) {
        java.util.List<com.org.peysen.bootmvc.entity.Order> orders = new java.util.ArrayList<>();
        for(int i=0;i<10;i++){
            com.org.peysen.bootmvc.entity.Order order = new com.org.peysen.bootmvc.entity.Order();
            order.setSource("pc:"+i);
            order.setPayMethod("wx:"+i);
            orders.add(order);
        }
        com.org.peysen.bootmvc.entity.Order order = new com.org.peysen.bootmvc.entity.Order();
        order.setSource("pc:0");
        order.setPayMethod("wx:0");
        orders.add(order);

        java.util.Map<String, com.org.peysen.bootmvc.entity.Order> orderMap = orders.stream().collect(java.util.stream.Collectors.toMap(order1 -> order1.getSource(), order1 -> order1, (v1, v2) -> v1));

        orderMap.keySet().stream().forEach(System.out::println);

    }
}
