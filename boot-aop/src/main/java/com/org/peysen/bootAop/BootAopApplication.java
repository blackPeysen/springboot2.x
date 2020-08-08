package com.org.peysen.bootAop;

import com.org.peysen.bootAop.service.AspectJServiceDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BootAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootAopApplication.class, args);

        AspectJServiceDemo aspectJServiceDemo = applicationContext.getBean("aspectJServiceDemo", AspectJServiceDemo.class);
        aspectJServiceDemo.test1();

        applicationContext.close();
    }

}
