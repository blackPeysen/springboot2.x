package com.org.peysen.bootrabbitmq.scheduler;

import com.org.peysen.bootrabbitmq.constants.Constans;
import com.org.peysen.bootrabbitmq.entity.MessageInfo;
import com.org.peysen.bootrabbitmq.entity.Order;
import com.org.peysen.bootrabbitmq.mapper.MessageLogRepository;
import com.org.peysen.bootrabbitmq.producer.RabbitMqSender;
import com.org.peysen.bootrabbitmq.util.DateUtil;
import com.org.peysen.bootrabbitmq.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 消息投递重试机制
 * @Author: peysen
 * @CreateDate: 2019/7/20 09:48
 * @UpdateRemark: The modified content
 */
@Component
public class RetryMessageScheduler {

    @Resource
    private MessageLogRepository messageLogRepository;

    @Autowired
    private RabbitMqSender rabbitMqSender;


    //@Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void retrySend(){
        System.out.println("定时任务开始。。。。");
        List<MessageInfo> messageInfoList = messageLogRepository.queryStatusAndTimeout();

        messageInfoList.forEach(messageInfo -> {
            if(messageInfo.getTryCount() >= 3){
                messageLogRepository.updateStatus(messageInfo.getMessageId(),Constans.ORDER_SEND_FAILURE,DateUtil.now());
            }else{
                messageLogRepository.updateRetryCount();
                Order order = (Order) FastJsonUtil.jsonToObject(messageInfo.getMessageContext(),Order.class);

                rabbitMqSender.sender(order);
            }
        });

    }


}
