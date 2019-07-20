package com.org.peysen.bootrabbitmq.service;

import com.org.peysen.bootrabbitmq.constants.Constans;
import com.org.peysen.bootrabbitmq.entity.MessageInfo;
import com.org.peysen.bootrabbitmq.entity.Order;
import com.org.peysen.bootrabbitmq.mapper.MessageLogRepository;
import com.org.peysen.bootrabbitmq.mapper.OrderRepository;
import com.org.peysen.bootrabbitmq.producer.RabbitMqSender;
import com.org.peysen.bootrabbitmq.util.DateUtil;
import com.org.peysen.bootrabbitmq.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/8 21:37
 * @UpdateRemark: The modified content
 */
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private MessageLogRepository messageLogRepository;

    @Autowired
    private RabbitMqSender rabbitMqSender;


    @Transactional(value="transactionManager",rollbackFor = Exception.class,timeout=36000)
    public void creareOrder(Order order){
        MessageInfo messageInfo = null;

        if(order != null) {
            try {
                //订单入库
                orderRepository.insertOrder(order);

                //构建订单消息日志记录对象
                messageInfo = new MessageInfo();
                messageInfo.setMessageId(order.getId());
                messageInfo.setMessageContext(FastJsonUtil.beanToJSONObject(order));
                messageInfo.setStatus(Constans.ORDER_SENDING);
                messageInfo.setNextRetry(DateUtil.addMinutes(DateUtil.now(), Constans.ORDER_TIMEOUT));
                messageInfo.setCreateTime(DateUtil.now());
                messageInfo.setUpdateTime(DateUtil.now());

                messageLogRepository.insert(messageInfo);

                //发送消息
                rabbitMqSender.sender(order);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public void insertOrder(Order order){

        if(order!=null){
            orderRepository.insertOrder(order);
        }

    }


}
