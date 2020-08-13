package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/9 07:45
 * @UpdateRemark: The modified content
 */
public class LogTopicSender implements Sender<Order> {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sender(Order order) {
        /**
         * 参数1：交换器名称
         * 参数2：路由键
         * 参数3：消息实体
         */
        this.rabbitTemplate.convertAndSend("alerts", "critical", order.getContext());

        this.rabbitTemplate.convertAndSend("alerts", "rate_limit", order.getContext());
    }
}
