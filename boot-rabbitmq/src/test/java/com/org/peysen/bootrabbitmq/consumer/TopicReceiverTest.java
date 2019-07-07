package com.org.peysen.bootrabbitmq.consumer;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 21:49
 * @UpdateRemark: The modified content
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicReceiverTest {

    @Autowired
    private TopicReceiver topicReceiver;

    @Test
    public void receiveTopic() {
        Order order=new Order();
        topicReceiver.receiveTopic(order);
    }
}