package com.org.peysen.bootrabbitmq.producer;

import com.org.peysen.bootrabbitmq.constants.Constans;
import com.org.peysen.bootrabbitmq.entity.Order;
import com.org.peysen.bootrabbitmq.mapper.MessageLogRepository;
import com.org.peysen.bootrabbitmq.util.DateUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/20 08:49
 * @UpdateRemark: The modified content
 */

@Component
public class RabbitMqSender implements Sender<Order> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageLogRepository messageLogRepository;


    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("correlationData:" + correlationData);

            Integer messageId = null;
            try{
                messageId = Integer.valueOf(correlationData.getId());
            }catch(Exception e){
                System.out.println("messageid is trans fault....");
            }


            if(messageId != null){
                if(ack){
                    //说明消息投递成功，则更新messageLog的状态
                    messageLogRepository.updateStatus(messageId, Constans.ORDER_SEND_SUCCESS, DateUtil.now());
                }else{
                    System.out.println("异常处理。。。。。");
                }
            }

        }
    };


    @Override
    public void sender(Order order) {
        CorrelationData correlationData = null;

        if(order != null){
            rabbitTemplate.setConfirmCallback(confirmCallback);
            correlationData = new CorrelationData(order.getId().toString());

            rabbitTemplate.convertAndSend(
                    "order-exchange",
                    "order.#",
                    order,
                    correlationData);
        }

    }
}
