package com.org.peysen.bootstrap;

import com.org.peysen.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:27
 * @UpdateRemark: The modified content
 */

@SpringBootApplication(scanBasePackages = "com.org.peysen.service")
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
