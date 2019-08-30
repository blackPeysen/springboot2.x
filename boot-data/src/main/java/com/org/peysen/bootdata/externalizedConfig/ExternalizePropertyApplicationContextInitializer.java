package com.org.peysen.bootdata.externalizedConfig;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * @Description: 使用ApplicationContextInitializer 扩展ConfigurableEnvironment
 * Created by mengmeng.Pei
 * 2019/8/30 11:20
 */
public class ExternalizePropertyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("4、ExternalizePropertyApplicationContextInitializer-->initialize");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",4);
        PropertySource propertySource = new MapPropertySource("from--initialize",hashMap);

        propertySources.addFirst(propertySource);
    }
}
