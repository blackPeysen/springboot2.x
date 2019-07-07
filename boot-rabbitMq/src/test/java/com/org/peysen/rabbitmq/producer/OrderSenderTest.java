package com.org.peysen.rabbitmq.producer;

import com.org.peysen.rabbitmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 19:16
 * @UpdateRemark: The modified content
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {


    @Autowired
    private OrderSender orderSender;

    @Test
    public void sender() {
        Order order=new Order();
        order.setId(20190707);
        order.setContext("hello world");
        order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());

        orderSender.sender(order);
    }
}