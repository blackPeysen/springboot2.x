package com.org.peysen.bootcommon.bootstrap;

import com.org.peysen.bootcommon.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: 自动转配引导类
 * @Author: peimm
 * @CreateDate: 2019/7/6 07:56
 * @UpdateRemark: The modified content
 */

@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println(helloWorld);
        //关闭上下文
        context.close();
    }

}
