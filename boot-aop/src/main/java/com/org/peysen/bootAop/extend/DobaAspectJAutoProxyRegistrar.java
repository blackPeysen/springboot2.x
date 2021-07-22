package com.org.peysen.bootAop.extend;

import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_09:17
 * @Desc:
 */
public class DobaAspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
//        AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);

        //AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry, new DobaAnnotationAwareAspectJAutoProxyCreator());
    }
}
