package com.org.peysen.bootmvc.config;

import com.org.peysen.bootmvc.interceptor.AccessSignAuthInterceptor;
import com.org.peysen.bootmvc.paramConverter.StringToDateConverter;
import com.org.peysen.bootmvc.paramConverter.StringToOrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 9:37
 */
//@EnableWebMvc
@Configurable
public class ParamConverterConfig implements WebMvcConfigurer {

    @Resource
    private AccessSignAuthInterceptor accessSignAuthInterceptor;

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

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


    /**
     * Description :
     * Group :
     * <p>
     * 实现自定义拦截器只需要3步
     * 1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。
     * 2、创建一个Java类继承WebMvcConfigurerAdapter，并重写 addInterceptors 方法。
     * 3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
     *
     * @param registry
     * @author honghh
     * @date 2019/3/22 0022 10:08
     * @author honghh
     * @date 2018/8/13 13:56
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(accessSignAuthInterceptor);
        // 拦截配置
        registration.addPathPatterns("/converter/**");
        // 排除配置
        registration.excludePathPatterns("/converter/test");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("ParamConverterConfig--addFormatters");
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToOrderConverter());
    }

    @PostConstruct
    public void init(){
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)requestMappingHandlerAdapter.getWebBindingInitializer();
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();

        if (initializer!=null){
            ConversionService conversionService = initializer.getConversionService();

            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            //添加字符串转换为list集合的转换机器
            genericConversionService.addConverter(new StringToDateConverter());
            //添加字符串转换为日期类型的字符串
            genericConversionService.addConverter(new StringToOrderConverter());
        }

    }

}
