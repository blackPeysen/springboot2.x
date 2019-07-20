package com.org.peysen.bootrabbitmq.consumer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Fanout 类型交换器监听器
 * @Author: peysen
 * @CreateDate: 2019/7/7 21:59
 * @UpdateRemark: The modified content
 */


@Component
public class FanoutReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void receiveTopic1(Order order) {
        System.out.println("【receiveFanout1监听到消息】" + order);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void receiveTopic2(Order order) {
        System.out.println("【receiveFanout2监听到消息】" + order);
    }

}

