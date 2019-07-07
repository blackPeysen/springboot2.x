package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 22:00
 * @UpdateRemark: The modified content
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSenderTest {
    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void sender() {
        Order order = new Order();
        order.setId(2018);
        order.setContext("Hello world");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());

        fanoutSender.sender(order);
    }
}