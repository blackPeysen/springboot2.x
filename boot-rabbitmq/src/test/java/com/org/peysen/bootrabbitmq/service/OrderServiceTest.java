package com.org.peysen.bootrabbitmq.service;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/8 22:05
 * @UpdateRemark: The modified content
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void creareOrderTest() {
        Order order = new Order();
//        order.setId(new Random().nextInt());
        order.setContext("Hello world");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());

        orderService.creareOrder(order);
    }


}