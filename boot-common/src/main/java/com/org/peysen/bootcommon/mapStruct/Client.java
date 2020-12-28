package com.org.peysen.bootcommon.mapStruct;

import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/28 13:57
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(12345L);
        order.setOrderSn("orderSn");
        order.setOrderType(0);
        order.setReceiverKeyword("keyword");
        order.setSourceType(1);
        order.setStatus(2);

        OrderMapStruct mapper = Mappers.getMapper(OrderMapStruct.class);
        OrderQueryParam orderQueryParam = mapper.orderToOrderQueryParam(order);

        assertEquals(orderQueryParam.getOrderSn(), order.getOrderSn());
        assertEquals(orderQueryParam.getOrderType(), order.getOrderType());
        assertEquals(orderQueryParam.getReceiverKey(), order.getReceiverKeyword());
        assertEquals(orderQueryParam.getSourceType(), order.getSourceType());
        assertEquals(orderQueryParam.getStatus(), order.getStatus());
    }
}
