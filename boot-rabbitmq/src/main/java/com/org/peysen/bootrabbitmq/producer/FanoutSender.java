package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 21:57
 * @UpdateRemark: The modified content
 */

@Component
public class FanoutSender implements Sender<Order> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sender(Order order) {

        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", order);

    }
}
