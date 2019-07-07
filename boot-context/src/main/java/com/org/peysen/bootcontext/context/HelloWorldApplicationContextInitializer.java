package com.org.peysen.bootcontext.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @Description: 初始化加载SpringApplication上下文，并进行调整
 * @Author: peimm
 * @CreateDate: 2019/7/7 08:16
 * @UpdateRemark: The modified content
 *  使用@Order注解实现加载优先级的调整
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("HelloWorldApplicationContextInitializer application id:"+applicationContext.getId());
    }
}
