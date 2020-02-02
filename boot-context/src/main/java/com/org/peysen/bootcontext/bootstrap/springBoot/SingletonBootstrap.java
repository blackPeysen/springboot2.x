package com.org.peysen.bootcontext.bootstrap.springBoot;

import com.org.peysen.bootcontext.config.SingletonConfig;
import com.org.peysen.bootcontext.entity.SingletonAutowired;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/24
 * @Desc : 单例模式自动注入
 *      org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)
 */

public class SingletonBootstrap {

    public static void main(String[] args) {

        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(SingletonConfig.class);
        ac.refresh();

        SingletonAutowired bean = ac.getBean(SingletonAutowired.class);
        System.out.println(bean.toString());

    }

}
