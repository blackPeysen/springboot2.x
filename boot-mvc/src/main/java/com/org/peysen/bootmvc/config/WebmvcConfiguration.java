package com.org.peysen.bootmvc.config;

import com.org.peysen.bootmvc.customized.argumentResolvers.OrderArgumentResolver;
import com.org.peysen.bootmvc.customized.converter.StringToDateConverter;
import com.org.peysen.bootmvc.customized.converter.StringToOrderConverter;
import com.org.peysen.bootmvc.customized.converter.StringToPropertiesConverter;
import com.org.peysen.bootmvc.customized.httpMessageConverter.OrderHttpMessageConverter;
import com.org.peysen.bootmvc.customized.interceptor.AccessSignAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;
import java.util.Locale;

/**
 * @Description: 国际化配置类
 *      添加SessionLocaleResolver 和 LocaleChangeInterceptor
 *      MessageSource 由 MessageSourceAutoConfiguration 自动注入,不需要开发者手动注入
 * Created by mengmeng.Pei
 * 2019/8/9 9:18
 */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebmvcConfiguration implements WebMvcConfigurer {

//    @PostConstruct
//    public void init(){
//        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
//        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<>(argumentResolvers.size()+1);
//        newResolvers.add(new PropestiesArgumentResolver());
//        newResolvers.addAll(argumentResolvers);
//
//        requestMappingHandlerAdapter.setArgumentResolvers(newResolvers);
//    }


    /**
     * 不推荐使用如下方法添加自定义HandlerMethodArgumentResolver
     *      原因：会导致自定义的HandlerMethodArgumentResolver 的顺序一直在内建PropestiesArgumentResolver之后
     *      推荐使用init（）方法对HandlerMethodArgumentResolver进行设置。
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        if (resolvers.isEmpty()){
            resolvers.add(new OrderArgumentResolver());
        }else {
            resolvers.set(0,new OrderArgumentResolver());
        }
    }


    @Override
    public  void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new OrderHttpMessageConverter());
    }


    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/login");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        //ThymeleafViewResolver Ordered.LOWEST_PRECEDEENCE - 5
        //优先级设置成高于 ThymeleafViewResolver
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        //配置 ViewResolver 的 Content-Type 页面采用 ?format=xml
        resolver.setContentType("text/xml;charset=UTF-8");
        return resolver;
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        // 默认语言
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }


    @Bean
    public HandlerInterceptor accessSignAuthInterceptor(){
        return new AccessSignAuthInterceptor();
    }

    /**
     * 实现自定义拦截器只需要3步
     * 1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。
     * 2、创建一个Java类继承WebMvcConfigurerAdapter，并重写 addInterceptors 方法。
     * 3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加localeChangeInterceptor拦截器
        registry.addInterceptor(localeChangeInterceptor());

        InterceptorRegistration registration = registry.addInterceptor(accessSignAuthInterceptor());
        // 拦截配置
        registration.addPathPatterns("/converter/*");
        // 排除配置
        registration.excludePathPatterns("/converter/test");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToOrderConverter());
        registry.addConverter(new StringToPropertiesConverter());
    }

}
