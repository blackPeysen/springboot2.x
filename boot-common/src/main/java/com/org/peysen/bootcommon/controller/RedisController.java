package com.org.peysen.bootcommon.controller;

import com.org.peysen.bootcommon.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/14 15:54
 */

@RestController
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/helloRedis")
    public void helloRedis(){

        Set<Object> test_zset = redisUtil.zrange("test_zset", 1, 3);
        Iterator<Object> iterator = test_zset.iterator();

        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println("object:" + next);
        }



    }

}
