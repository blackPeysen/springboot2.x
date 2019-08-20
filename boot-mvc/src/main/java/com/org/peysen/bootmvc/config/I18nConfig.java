package com.org.peysen.bootmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @Description: 国际化配置类
 *      添加SessionLocaleResolver 和 LocaleChangeInterceptor
 *      MessageSource 由 MessageSourceAutoConfiguration 自动注入,不需要开发者手动注入
 * Created by mengmeng.Pei
 * 2019/8/9 9:18
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

//    @Bean(name = "messageSource")
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("i18n/login");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
