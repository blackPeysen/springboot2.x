package com.org.peysen.rabbitmq.producer;

import com.org.peysen.rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 18:55
 * @UpdateRemark: The modified content
 */

@Component("orderSender")
public class OrderSender implements Sender<Order>{

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sender(Order order) {
        CorrelationData correlationData=new CorrelationData();
        correlationData.setId(order.getMessageId());

        rabbitTemplate.convertAndSend(
                "order-exchange",
                "order-adbc",
                order,
                correlationData);

    }
}
