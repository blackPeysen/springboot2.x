package com.org.peysen.bootcontext.context.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * @Description: 自定义SpringApplication 上下文监听器：监听 ContextRefreshedEvent 事件
 * @Author: peimm
 * @CreateDate: 2019/7/7 08:24
 * @UpdateRemark: The modified content
 */
public class AfterHelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent>,Ordered {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("AfterHelloWorldApplicationListener:"+ event.getApplicationContext().getId()
                + "timestamp:" +  event.getTimestamp());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
