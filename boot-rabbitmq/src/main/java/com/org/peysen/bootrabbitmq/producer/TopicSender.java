package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/9 07:45
 * @UpdateRemark: The modified content
 */
public class TopicSender implements Sender<Order> {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sender(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,"lzc.message", order);
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "lzc.lzc", order);
    }
}
