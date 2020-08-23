package com.org.peysen.bootcontext.externalizedConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * @Description: 实现EnvironmentPostProcessor接口 扩展ConfigurableEnvironment
 * Created by mengmeng.Pei
 * 2019/8/30 11:11
 */
public class ExternalizePropertyPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("3、ExternalizePropertyPostProcessor-->postProcessEnvironment");
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",3);
        PropertySource propertySource = new MapPropertySource("from--postProcessEnvironment",hashMap);

        propertySources.addFirst(propertySource);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
