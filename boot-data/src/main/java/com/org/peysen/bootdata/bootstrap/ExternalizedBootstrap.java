package com.org.peysen.bootdata.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/30 11:15
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.org.peysen.bootdata.externalizedConfig")  //扫描指定包下的类
public class ExternalizedBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ExternalizedBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        ConfigurableEnvironment environment = context.getEnvironment();

        System.err.println("测试扩展外部化配置结果: "  + environment.getProperty("user.id"));
        //打印所有资源配置对象
        environment.getPropertySources().forEach(propertySource -> {
            System.err.printf("PropertySource[名称:%s]  :  %s\n",propertySource.getName(),propertySource);
            System.out.println();
        });
        // 关闭上下文
        context.close();
    }
}
