package com.org.peysen.bootstrap;

import com.org.peysen.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:51
 * @UpdateRemark: The modified content
 */

@Configuration
public class OnSystemPropertyConditionBootstrap {

    @Bean
    @ConditionalOnSystemProperty(name = "userName",value = "peysen")
    public String helloWorld(){
        return "hello world conditionalOnSystemProperty";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OnSystemPropertyConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        context.close();


    }
}
