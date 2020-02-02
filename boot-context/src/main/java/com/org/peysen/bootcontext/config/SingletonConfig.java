package com.org.peysen.bootcontext.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/24
 * @Desc :
 */

@ComponentScan("com.org.peysen.bootcontext.entity")
@Configuration
public class SingletonConfig {

//    @Bean
//    public SingletonAutowired singletonAutowired(){
//        return new SingletonAutowired();
//    }
}
