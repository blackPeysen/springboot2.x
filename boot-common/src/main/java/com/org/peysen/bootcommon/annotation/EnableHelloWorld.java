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
//@Import(HelloWorldConfiguration.class)
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {

}
