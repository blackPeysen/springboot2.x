package com.org.peysen.bootrabbitmq.consumer;

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
public class LogTopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(bindings = @QueueBinding(
                            value = @Queue("critical"),
                            exchange = @Exchange("alerts"),
                            key = "critical.*"))
    public void receiveTopic(Order order) {
        System.out.println("【LogTopicReceiver1队列监听到消息】" + order.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("rate_limit"),
            exchange = @Exchange("alerts"),
            key = "*.rate_limit"))
    public void receiveTopic1(Order order) {
        System.out.println("【LogTopicReceiver2队列监听到消息】" + order.toString());
    }

}
