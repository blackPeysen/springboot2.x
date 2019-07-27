package com.org.peysen.bootcontext.bootstrap.spring;

import com.org.peysen.bootcontext.entity.Knight;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description: Link {ClassPathXmlApplicationContext}
 * @Author: peysen
 * @CreateDate: 2019/7/20 17:19
 * @UpdateRemark: 深度了解xml配置文件的bean加载逻辑
 */
@SpringBootApplication
public class XmlBeanFactoryBootstarp {

    public static void main(String[] args) {

        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("META-INF/spring/knights.xml"));

        Object knight1 = beanFactory.getBean("knight");
        Knight knight = beanFactory.getBean(Knight.class);
        knight.enbarkOnQuest();
    }

}
