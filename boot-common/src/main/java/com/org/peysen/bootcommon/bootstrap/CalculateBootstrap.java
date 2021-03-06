package com.org.peysen.bootcommon.bootstrap;

import com.org.peysen.bootcommon.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: 基于配置方式实现 @Profile
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:27
 * @UpdateRemark: The modified content
 */

@SpringBootApplication(scanBasePackages = "com.org.peysen.bootcommon.service")
public class CalculateBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java8")
                .run(args);

        CalculateService calculateService=context.getBean(CalculateService.class);
        Integer sum = calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(sum);

        context.close();
    }
}
