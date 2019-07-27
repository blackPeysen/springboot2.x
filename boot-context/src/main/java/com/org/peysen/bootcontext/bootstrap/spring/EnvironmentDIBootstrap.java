package com.org.peysen.bootcontext.bootstrap.spring;

import com.org.peysen.bootcontext.bootstrap.springBoot.XmlConfigPlaceholderConfigurationBootstarp;
import com.org.peysen.bootcontext.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @Description: @Value 构造器注入,还可以使用方法注入，在user（）中使用@Value注解
 * @Author: peimm
 * @CreateDate: 2019/7/6 09:37
 * @UpdateRemark: The modified content
 */

@EnableAutoConfiguration
public class EnvironmentDIBootstrap implements EnvironmentAware, BeanFactoryAware {

//    @Autowired
    private Environment environment;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.environment = beanFactory.getBean(Environment.class);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    private User user(){
        Long id = environment.getRequiredProperty("user.id", Long.class);
        String userName = environment.getRequiredProperty("user.name");
        Integer age = environment.getProperty("user.age", Integer.class,
                environment.getProperty("user.age", Integer.class, 19));


        User user = new User();
        user.setAge(age);
        user.setId(id);
        user.setUserName(userName);

        return  user;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(XmlConfigPlaceholderConfigurationBootstarp.class)
                .web(WebApplicationType.NONE)
                .run(args);

        User user = context.getBean(User.class);

        System.err.println("用户对象：" + user);

        //关闭上下文
        context.close();
    }


}
