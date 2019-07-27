package com.org.peysen.bootcontext.bootstrap.spring;

import com.org.peysen.bootcontext.bootstrap.springBoot.XmlConfigPlaceholderConfigurationBootstarp;
import com.org.peysen.bootcontext.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description: @Value 构造器注入,还可以使用方法注入，在user（）中使用@Value注解
 * @Author: peimm
 * @CreateDate: 2019/7/6 09:37
 * @UpdateRemark: The modified content
 */

@EnableAutoConfiguration
public class ValueAnnotationBootstrap {

    private final Long id;

    private final String name;

    private final Integer age;

    public ValueAnnotationBootstrap(@Value("${user.id}") Long id,
                                    @Value("${user.name}")String name,
                                    @Value("${user.age:25}")Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Bean
    private User user(){
        User user = new User();
        user.setAge(age);
        user.setId(id);
        user.setUserName(name);

        return  user;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(XmlConfigPlaceholderConfigurationBootstarp.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean(User.class);

        System.out.println("用户对象：" + user);

        //关闭上下文
        context.close();
    }
}
