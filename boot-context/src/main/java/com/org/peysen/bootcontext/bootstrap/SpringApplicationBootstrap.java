package com.org.peysen.bootcontext.bootstrap;

import com.org.peysen.bootcontext.BootContextApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: SpringAppcalition 引导类
 * @Author: peimm
 * @CreateDate: 2019/7/6 09:37
 * @UpdateRemark: The modified content
 */

@SpringBootApplication
public class SpringApplicationBootstrap {

    public static void main(String[] args) {

        Set<String> sources=new HashSet<>();

        sources.add(BootContextApplication.class.getName());

        SpringApplication springApplication=new SpringApplication();
        springApplication.setSources(sources);
        ConfigurableApplicationContext context = springApplication.run(args);
        System.out.println("Springboot bean:"+context.getBean(BootContextApplication.class));
    }
}
