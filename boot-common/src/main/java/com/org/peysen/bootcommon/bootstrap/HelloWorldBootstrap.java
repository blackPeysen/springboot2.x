package com.org.peysen.bootcommon.bootstrap;

import com.org.peysen.bootcommon.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: HelloWorld 引导类
 * @Author: peimm
 * @CreateDate: 2019/7/3 06:54
 * @UpdateRemark: The modified content
 */

@EnableHelloWorld
public class HelloWorldBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(HelloWorldBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        context.close();
    }
}
