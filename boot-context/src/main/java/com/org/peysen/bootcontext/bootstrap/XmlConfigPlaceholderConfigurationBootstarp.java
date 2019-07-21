package com.org.peysen.bootcontext.bootstrap;

import com.org.peysen.bootcontext.entity.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Spring xml 配置占位符引导类
 * @Author: peysen
 * @CreateDate: 2019/7/20 17:19
 * @UpdateRemark: 深度了解xml配置文件的bean加载逻辑
 */
@ImportResource("META-INF/spring/user-context.xml")
@EnableAutoConfiguration
public class XmlConfigPlaceholderConfigurationBootstarp {

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
