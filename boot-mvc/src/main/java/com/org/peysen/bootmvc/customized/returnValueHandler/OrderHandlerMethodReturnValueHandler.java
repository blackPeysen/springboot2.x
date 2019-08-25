package com.org.peysen.bootmvc.customized.returnValueHandler;

import com.org.peysen.bootmvc.customized.httpMessageConverter.OrderHttpMessageConverter;
import com.org.peysen.bootmvc.entity.Order;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * @author : peysen
 * @Desc:
 * @date :
 */
public class OrderHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Order.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // Servlet API
        ServletWebRequest servletWebRequest = (ServletWebRequest)webRequest;
        HttpServletRequest servletRequest = servletWebRequest.getRequest();
        HttpServletResponse servletResponse = servletWebRequest.getResponse();

        String contentType = servletRequest.getHeader("Content-type");
        MediaType mediaType = MediaType.parseMediaType(contentType);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(servletResponse);


        //复用OrderHttpMessageConverter.readInternal()
        OrderHttpMessageConverter orderHttpMessageConverter = new OrderHttpMessageConverter();
        Order order = (Order) returnValue;
        orderHttpMessageConverter.write(order,mediaType,outputMessage);

    }
}
