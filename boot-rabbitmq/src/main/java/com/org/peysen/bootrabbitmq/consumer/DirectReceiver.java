package com.org.peysen.bootrabbitmq.consumer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: direct 类型交换器监听器
 * @Author: peysen
 * @CreateDate: 2019/7/9 07:44
 * @UpdateRemark: The modified content
 */
@Component
public class DirectReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect1(Order order, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("【receiveDirect1监听到消息】" + order);
    }

//    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect2(Order order) {
        System.out.println("【receiveDirect2监听到消息】" + order);
    }
}
