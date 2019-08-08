package com.org.peysen.bootmvc.controller;

import com.org.peysen.bootmvc.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 9:44
 */
@Controller
@RequestMapping("/converter")
public class ConverterController {
    private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    /**
     *
     * @param order 订单信息
     * @return  请求方式为application/x-www-form-urlencoded
     */
    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded",produces = "application/json")
    public Object checkInventoryForm(Order order){
        if(order==null){
            throw new RuntimeException("the hotelOrder is null");
        }
        return order;
    }
}
