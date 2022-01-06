package com.org.peysen.bootAop.bootStrap;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;


/**
 * @author peimengmeng
 * @since 2021-09-08 12:06
 * @description 单点调用切面注册
 *
 */
public class SinglePointSpringBootStrap implements ApplicationListener<ContextRefreshedEvent>, PointcutAdvisor, Ordered {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            getBean(SinglePointServiceImpl.class).initAll();
            ConfigHolder.printAllClientConfig();
        }
    }

    @Override
    public Advice getAdvice() {
        return new SinglePointClient();
    }

    @Override
    public Pointcut getPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(ConfigHolder.getAdvisorExpression());
        return pointcut;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    @Override
    public int getOrder() {
        return ConfigHolder.getKeyAdvisorOrder();
    }

}
