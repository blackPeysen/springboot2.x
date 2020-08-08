package com.org.peysen.bootcontext.bootstrap.springBoot;

import com.org.peysen.bootcontext.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/4
 * @Desc :
 */
@SpringBootApplication(scanBasePackages = "com.org.peysen.bootcontext.model")
public class FactoryBeanBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FactoryBeanBootstrap.class, args);

        User bean = context.getBean("userFactoryBean", User.class);

        System.out.println(bean);

        context.close();
    }
}
