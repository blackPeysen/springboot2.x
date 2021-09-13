package com.org.peysen.bootAop.extend;

import com.org.peysen.bootAop.annotation.CrovRpc;
import com.org.peysen.bootAop.aspectJ.ExecutionAspectJDemo;
import com.org.peysen.bootAop.service.AspectJServiceImpl;
import com.org.peysen.bootAop.service.CrovRpcServiceImpl;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAwareMethodMatcher;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.aspectj.annotation.AbstractAspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectInstanceFactory;
import org.springframework.aop.aspectj.annotation.MetadataAwareAspectInstanceFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: peimengmeng
 * @Date: 2021/7/17_08:48
 * @Desc:
 */
public class DobaAnnotationAwareAspectJAutoProxyCreator extends AnnotationAwareAspectJAutoProxyCreator {
    @Nullable
    private volatile List<String> aspectBeanNames;

    private final Map<String, List<Advisor>> advisorsCache = new ConcurrentHashMap<>();

    private ExecutionAspectJDemo demo = new ExecutionAspectJDemo();

    //private final ParameterNameDiscoverer parameterNameDiscoverer = new DobaAspectJAnnotationParameterNameDiscoverer();


    @Override
    public List<Advisor> findCandidateAdvisors() {
        List<Advisor> candidateAdvisors = super.findCandidateAdvisors();
        candidateAdvisors.addAll(getAdvisors());

        return candidateAdvisors;
    }


    @Override
    public int getOrder() {
        return super.getOrder() + 1;
    }

    private List<Advisor> getAdvisors(){
        List<String> aspectNames = this.aspectBeanNames;
        ListableBeanFactory beanFactory = (ListableBeanFactory) getBeanFactory();


        if (aspectNames == null) {
            synchronized (this) {
                aspectNames = this.aspectBeanNames;
                if (aspectNames == null) {
                    aspectNames = new ArrayList<>();
                    List<Advisor> advisors = new ArrayList<>();

                    String[] beanNames = BeanFactoryUtils.beanNamesForAnnotationIncludingAncestors(beanFactory, CrovRpc.class);
                    for (String beanName : beanNames) {
                        Class<?> beanClass = beanFactory.getType(beanName);
                        if (beanClass == null) {
                            continue;
                        }
                        if (isAspect(beanClass)) {
                            aspectNames.add(beanName);
                            List<Advisor> advisors1 = getAdvisors(beanClass);
                            advisors.addAll(advisors1);
                            this.advisorsCache.put(beanName, advisors1);
                        }
                    }

                    this.aspectBeanNames = aspectNames;
                    return advisors;
                }
            }
        }

        if (aspectNames.isEmpty()) {
            return Collections.emptyList();
        }

        List<Advisor> advisors = new ArrayList<>();
        for (String aspectName : aspectNames) {
            List<Advisor> cachedAdvisors = this.advisorsCache.get(aspectName);
            if (cachedAdvisors != null) {
                advisors.addAll(cachedAdvisors);
            }
        }

        return advisors;
    }

    private boolean isAspect(Class<?> clazz) {
        return AnnotationUtils.findAnnotation(clazz, CrovRpc.class) != null;
    }


    public List<Advisor> getAdvisors(Class<?> aspectClass) {
        List<Advisor> advisors = new ArrayList<>();

        Advisor advisor = getAdvisor(null, aspectClass);
        if (advisor != null) {
            advisors.add(advisor);
        }
//        for (Method method : aspectClass.getDeclaredMethods()) {
//            if (Modifier.isPublic(method.getModifiers())){
//                System.out.println("method:" + method.getName());
//                Advisor advisor = getAdvisor(method, aspectClass);
//                if (advisor != null) {
//                    advisors.add(advisor);
//                }
//            }
//        }

        return advisors;
    }

    public Advisor getAdvisor(Method method, Class<?> aspectClass){
        AspectJExpressionPointcut expressionPointcut = getPointcut(method, aspectClass);
        if (expressionPointcut == null) {
            return null;
        }
        // todo : this
        SingletonAspectInstanceFactory instanceFactory = new SingletonAspectInstanceFactory(demo);

        AbstractAspectJAdvice advice = new AspectJAfterReturningAdvice(aspectJBeforeAdviceMethod(), expressionPointcut, instanceFactory);

        // Now to configure the advice...
        advice.setDeclarationOrder(getOrder());

        // todo : 参数
        String[] argNames = null; //this.parameterNameDiscoverer.getParameterNames(candidateAdviceMethod);
        if (argNames != null) {
            advice.setArgumentNamesFromStringArray(argNames);
        }
        advice.calculateArgumentBindings();

        return new DefaultPointcutAdvisor(expressionPointcut, advice);
    }

    @Nullable
    private static AspectJExpressionPointcut getPointcut(Method candidateAdviceMethod, Class<?> candidateAspectClass) {
        String className = candidateAspectClass.getName();
//        String methodName = candidateAdviceMethod.getName();
        String expression = "execution(public * " +  className + ".*" +"(..))";

        AspectJExpressionPointcut ajexp =
                new AspectJExpressionPointcut(candidateAspectClass, new String[0], new Class<?>[0]);
        ajexp.setExpression(expression);

        return ajexp;
    }

    private Method aspectJBeforeAdviceMethod(){
        Method method = null;
        Class aClass = this.demo.getClass();
        try {
            method = aClass.getDeclaredMethod("advise");
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return aClass.getDeclaredMethods()[0];
    }

    public String test1(){
        System.out.println("test DOba 。。。。");

        return "+++++++";
    }


    public static void main(String[] args) {
        boolean hasIntroductions = false;
        Class clazz = CrovRpcServiceImpl.class;
        AspectJExpressionPointcut pointcut = getPointcut(null, clazz);

        MethodMatcher methodMatcher = pointcut.getMethodMatcher();
        IntroductionAwareMethodMatcher introductionAwareMethodMatcher = null;
        if (methodMatcher instanceof IntroductionAwareMethodMatcher) {
            introductionAwareMethodMatcher = (IntroductionAwareMethodMatcher) methodMatcher;
        }

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
        for (Method method : methods) {
            if (introductionAwareMethodMatcher != null ?
                    introductionAwareMethodMatcher.matches(method, clazz, hasIntroductions) :
                    methodMatcher.matches(method, clazz)) {
                System.out.println("-----");
            }
        }
    }

}
