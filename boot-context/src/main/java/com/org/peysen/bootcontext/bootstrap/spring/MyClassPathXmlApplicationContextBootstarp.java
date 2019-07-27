package com.org.peysen.bootcontext.bootstrap.spring;

import com.org.peysen.bootcontext.context.support.MyClassPathXmlApplicationContext;
import com.org.peysen.bootcontext.entity.Knight;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 继承 {ClassPathXmlApplicationContext}，实现对initPropertySources()的重写
 * @Author: peysen
 * @CreateDate: 2019/7/20 17:19
 * @UpdateRemark: 深度了解xml配置文件的bean加载逻辑
 */
@SpringBootApplication
public class MyClassPathXmlApplicationContextBootstarp {

    public static void main(String[] args) {
        ApplicationContext context = new MyClassPathXmlApplicationContext(
                "META-INF/spring/knights.xml");

        Knight knight = context.getBean(Knight.class);
        knight.enbarkOnQuest();
    }

}
