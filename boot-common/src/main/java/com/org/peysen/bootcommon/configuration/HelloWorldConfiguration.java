package com.org.peysen.bootcommon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: hello world bean 配置
 * @Author: peimm
 * @CreateDate: 2019/7/3 06:52
 * @UpdateRemark: The modified content
 */

//@Configuration
public class HelloWorldConfiguration {


    @Bean
    public String helloWorld(){//默认方法名即bean名称
        return "hello world 2018";
    }
}
