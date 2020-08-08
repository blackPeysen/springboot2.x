package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/9 07:42
 * @UpdateRemark: 直连发布者
 */
@Component
public class DirectSender implements Sender<Order>{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sender(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.pwl", order);
    }
}
