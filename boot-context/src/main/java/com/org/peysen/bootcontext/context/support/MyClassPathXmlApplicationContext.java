package com.org.peysen.bootcontext.context.support;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: 继承ClassPathXmlApplicationContext，重写initPropertySources（）
 *                  实现对系统属性及环境变量的初始化及验证。
 * @Author: peysen
 * @CreateDate: 2019/7/27 15:41
 * @UpdateRemark: The modified content
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String... configLocations) {
        super(configLocations);
    }

    @Override
    protected void initPropertySources() {
        super.initPropertySources();
        getEnvironment().setRequiredProperties("test");
    }
}
