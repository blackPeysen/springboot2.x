package com.org.peysen.bootcontext.externalizedConfig;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * @Description:
 *  使用ApplicationListener 监听ApplicationEnvironmentPreparedEvent事件
 *     onApplicationEvent() 扩展ConfigurableEnvironment
 * Created by mengmeng.Pei
 * 2019/8/30 11:05
 */
public class ExternalizePropertyEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();

        System.out.println("2、ExternalizePropertyEventListener-->onApplicationEvent");
        MutablePropertySources propertySources = environment.getPropertySources();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user.id",2);
        PropertySource propertySource = new MapPropertySource("from--onApplicationEvent",hashMap);

        propertySources.addFirst(propertySource);
    }
}
