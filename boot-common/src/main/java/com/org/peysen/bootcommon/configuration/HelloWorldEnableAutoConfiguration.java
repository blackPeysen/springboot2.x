package com.org.peysen.bootcommon.configuration;

import com.org.peysen.bootcommon.annotation.EnableHelloWorld;
import com.org.peysen.bootcommon.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: hello world bean 自动装配
 * @Author: peimm
 * @CreateDate: 2019/7/3 06:52
 * @UpdateRemark: The modified content
 */

@Configuration      //spring 模式注解装配
@EnableHelloWorld   //Spring @Enable模块装配
@ConditionalOnSystemProperty(name = "userName",value = "peysen") //条件装配
public class HelloWorldEnableAutoConfiguration {


}
