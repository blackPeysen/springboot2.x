package com.org.peysen.bootcontext.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Description: SpringApplication 上下文监听器
 * @Author: peimm
 * @CreateDate: 2019/7/7 08:24
 * @UpdateRemark: The modified content
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("HelloWorldApplicationListener:"+event.getTimestamp());
    }
}
