package com.org.peysen.bootcommon.bootstrap;

import com.org.peysen.bootcommon.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 使用@ConditionalOnSystemProperty条件装配
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
