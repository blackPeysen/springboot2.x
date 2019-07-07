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
 *  实现Ordered接口以进行优先级的调整
 */

public class AfterHelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer,Ordered {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        System.out.println("AfterHelloWorldApplicationContextInitializer application id:"+applicationContext.getId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
