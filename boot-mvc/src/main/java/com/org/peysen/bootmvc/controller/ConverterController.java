package com.org.peysen.bootmvc.controller;

import com.org.peysen.bootmvc.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 自定义数据类型转换器
 * Created by mengmeng.Pei
 * 2019/8/8 9:44
 */
@RestController
@RequestMapping("/converter")
public class ConverterController {
    private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    /**
     * 根据@RequestBody
     *      将Content-type=application/json 的请求参数 使用 OrderHttpMessageConverter 转换Order
     * @param   order 订单信息
     * @return  Object
     */
    @RequestMapping(value = "/ ",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Object testOrder(@RequestBody Order order){
        if(order==null){
            throw new RuntimeException("the hotelOrder is null");
        }
        return order;
    }

    /**
     * 根据@RequestBody
     *      将Content-type=text/properties 的请求参数 使用 OrderHttpMessageConverter 转换Order
     * @param order
     * @return
     */
    @PostMapping(value = "/loadHttpOrder",
            consumes = "text/properties")
    public String propertiesHttpConverter(@RequestBody Order order){

        return order.toString();
    }

    /**
     * 根据没有@RequestBody
     *      使用OrderArgumentResolver 将请求方式为application/json
     *      将请求参数映射成 Order
     * @param   order
     * @return  String
     */
    @RequestMapping(value = "/loadResolver",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public String loadResolver(Order order){
        if(order==null){
            throw new RuntimeException("the hotel Order is null");
        }
        return order.toString();
    }

}
