package com.org.peysen.bootAop.bootStrap;

import com.org.peysen.bootAop.service.CrovServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "com.org.peysen.bootAop")
@SpringBootApplication
@EnableAspectJAutoProxy
public class BootAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootAopApplication.class, args);

        CrovServiceImpl crovService = applicationContext.getBean("crovServiceImpl", CrovServiceImpl.class);
        crovService.invoke();

        applicationContext.close();
    }

}