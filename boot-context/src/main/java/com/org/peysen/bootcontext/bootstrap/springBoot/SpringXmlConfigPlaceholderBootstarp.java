package com.org.peysen.bootcontext.bootstrap.springBoot;

import com.org.peysen.bootcontext.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Spring xml 配置占位符引导类
 * @Author: peysen
 * @CreateDate: 2019/7/20 17:19
 * @UpdateRemark: 深度了解xml配置文件的bean加载逻辑
 */
@SpringBootApplication
public class SpringXmlConfigPlaceholderBootstarp {

    public static void main(String[] args) {

        String[] locations = {"META-INF/spring/spring-context.xml",
                              "META-INF/spring/user-context.xml"};

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);

        User user = context.getBean(User.class);

        System.out.println("用户对象：" + user);

        //关闭上下文
        context.close();
    }

}
