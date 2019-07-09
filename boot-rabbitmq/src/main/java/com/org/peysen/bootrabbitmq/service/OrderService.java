package com.org.peysen.bootrabbitmq.service;

import com.org.peysen.bootrabbitmq.entity.Order;
import com.org.peysen.bootrabbitmq.repository.OrderRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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



    public void insertOrder(Order order){

        if(order!=null){
            orderRepository.insertOrder(order);
        }

    }
    public Order queryOrderById(Integer id){
        Order order = null;

        if (id != null){
            order = orderRepository.findById(id);
        }

        return order;
    }

}
