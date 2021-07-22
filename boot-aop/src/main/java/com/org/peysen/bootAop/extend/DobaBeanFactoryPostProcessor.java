package com.org.peysen.bootAop.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import static org.springframework.aop.config.AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/22_09:59
 * @Desc:
 */
@Component
public class DobaBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanFactory.getBeanDefinition(AUTO_PROXY_CREATOR_BEAN_NAME);

        beanDefinition.setBeanClass(DobaAnnotationAwareAspectJAutoProxyCreator.class);
    }
}
