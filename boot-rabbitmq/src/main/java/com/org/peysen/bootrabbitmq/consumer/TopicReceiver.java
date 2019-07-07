package com.org.peysen.bootrabbitmq.consumer;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 21:47
 * @UpdateRemark: The modified content
 */


@Component
public class TopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = "order-queue")
    public void receiveTopic(Order order) {
        System.out.println("【receiveTopic1监听到消息】" + order.toString());

    }

}
