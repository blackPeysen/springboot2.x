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
 * @Author Peysen
 * @Date 2020/8/16 09:38
 * @Desc TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicSenderTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void sender() {
        Order order = new Order();
        order.setId(2018);
        order.setContext("Hello world");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());

        topicSender.sender(order);
    }
}