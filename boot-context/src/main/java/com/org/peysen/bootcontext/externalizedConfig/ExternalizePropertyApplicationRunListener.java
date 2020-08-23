package com.org.peysen.bootcontext.externalizedConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * @Description: 使用SpringApplicationRunListener#environmentPrepared() 扩展ConfigurableEnvironment
 * Created by mengmeng.Pei
 * 2019/8/30 10:55
 */
public class ExternalizePropertyApplicationRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;

    private final String[] args;


    public ExternalizePropertyApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("1、ExternalizePropertyApplicationRunListener-->environmentPrepared");
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",1);
        PropertySource propertySource = new MapPropertySource("from--environmentPrepared",hashMap);

        propertySources.addFirst(propertySource);

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("5、ExternalizePropertyApplicationRunListener-->contextPrepared");
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",5);
        PropertySource propertySource = new MapPropertySource("from--contextPrepared",hashMap);

        propertySources.addFirst(propertySource);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("6、ExternalizePropertyApplicationRunListener-->contextLoaded");
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",6);
        PropertySource propertySource = new MapPropertySource("from--contextLoaded",hashMap);

        propertySources.addFirst(propertySource);

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        return new EventPublishingRunListener(application,args).getOrder() + 1;
    }
}
