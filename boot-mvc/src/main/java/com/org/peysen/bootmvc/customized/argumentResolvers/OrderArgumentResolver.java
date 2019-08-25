package com.org.peysen.bootmvc.customized.argumentResolvers;

import com.org.peysen.bootmvc.customized.httpMessageConverter.OrderHttpMessageConverter;
import com.org.peysen.bootmvc.entity.Order;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc: 自定义ArgumentResolver
 *          用于将未使用@RequestBody的请求参数转换为Order
 * @author : peysen
 * @date : 2019-08-25
 */
public class OrderArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Order.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // Servlet API
        ServletWebRequest servletWebRequest = (ServletWebRequest)webRequest;
        HttpServletRequest request = servletWebRequest.getRequest();

        //复用OrderHttpMessageConverter.readInternal()
        OrderHttpMessageConverter orderHttpMessageConverter = new OrderHttpMessageConverter();
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        Order order = orderHttpMessageConverter.read(null, null, inputMessage);

        return order;
    }
}
