package com.org.peysen.bootcontext.entity;

import org.springframework.stereotype.Component;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/24
 * @Desc :
 */

@Component
public class SingletonAutowired {

    private Integer a;

    public SingletonAutowired(){
        System.out.println("SingletonAutowired  is created....");
    }
}
