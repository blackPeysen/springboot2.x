package com.org.peysen.bootcontext.bootstrap.spring;

import com.org.peysen.bootcontext.entity.Knight;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @Description: Link {ClassPathXmlApplicationContext}
 * @Author: peysen
 * @CreateDate: 2019/7/20 17:19
 * @UpdateRemark: 深度了解xml配置文件的bean加载逻辑
 */
@SpringBootApplication
public class ClassPathXmlApplicationContextBootstarp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/spring/knights.xml");

        Knight knight = context.getBean(Knight.class);
        knight.enbarkOnQuest();
    }
}
