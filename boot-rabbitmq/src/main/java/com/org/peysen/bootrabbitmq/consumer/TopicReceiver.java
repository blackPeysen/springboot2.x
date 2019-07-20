package com.org.peysen.bootrabbitmq.consumer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Topic 类型交换器监听器
 * @Author: peysen
 * @CreateDate: 2019/7/7 21:47
 * @UpdateRemark: The modified content
 */


@Component
public class TopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = "order-queue")
    public void receiveTopic(Order order) {
        System.out.println("【order-queue队列监听到消息】" + order.toString());
    }

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(Order order) {
        System.out.println("【receiveTopic1监听到消息】" + order.toString());
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void receiveTopic2(Order order) {
        System.out.println("【receiveTopic2监听到消息】" + order.toString());
    }

}
