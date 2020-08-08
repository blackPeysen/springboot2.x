package com.org.peysen.bootcontext.bootstrap.springBoot;

import com.org.peysen.bootcontext.service.ServiceA;
import com.org.peysen.bootcontext.service.ServiceB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/2
 * @Desc : 循环依赖
 */
@SpringBootApplication(scanBasePackages = "com.org.peysen.bootcontext.service")
public class CircularDependencyBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CircularDependencyBootstrap.class, args);

        ServiceA serviceA = context.getBean(ServiceA.class);
        ServiceB serviceB = context.getBean(ServiceB.class);

        System.out.println(serviceA);
        System.out.println(serviceB.getServiceA());

        System.out.println(serviceB);
        System.out.println(serviceA.getServiceB());

        context.close();
    }
}
