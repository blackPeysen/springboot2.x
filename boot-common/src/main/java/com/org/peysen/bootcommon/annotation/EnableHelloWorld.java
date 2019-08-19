package com.org.peysen.bootcommon.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description: Enable注解模块装配
 * @Author: peimm
 * @CreateDate: 2019/7/3 06:50
 * @UpdateRemark: The modified content
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) //直接导入一个Configuration配置类
@Import(HelloWorldImportSelector.class)  //更具有弹性
public @interface EnableHelloWorld {

}
