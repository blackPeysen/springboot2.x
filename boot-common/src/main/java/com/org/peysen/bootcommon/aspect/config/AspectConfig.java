package com.org.peysen.bootcommon.aspect.config;

import com.org.peysen.bootcommon.aspect.Audience;
import com.org.peysen.bootcommon.aspect.Performance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/23 07:26
 * @UpdateRemark: The modified content
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AspectConfig {


    @Bean
    public Performance performance(){
        return new Performance();
    }

    @Bean
    public Audience audience(){
        return  new Audience();
    }

}
