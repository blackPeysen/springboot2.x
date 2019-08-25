package com.org.peysen.bootmvc.customized.httpMessageConverter;

import com.alibaba.fastjson.JSON;
import com.org.peysen.bootmvc.entity.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Desc: 自定义HttpMessageConverter
 *          {@Link Order} {@Link  AbstractGenericHttpMessageConverter} 实现
 * Author:Peysen
 * Date:2019-08-23
 */
public class OrderHttpMessageConverter extends AbstractGenericHttpMessageConverter<Order> {


    public OrderHttpMessageConverter() {
        super(new MediaType("text","properties"));
    }

    @Override
    protected void writeInternal(Order order, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //order ->String
        HttpHeaders headers = outputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = contentType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        //字节输出流
        OutputStream outputStream = outputMessage.getBody();
        //字符输出流
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,charset);

        writer.write(order.toString());

    }

    @Override
    protected Order readInternal(Class<? extends Order> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        HttpHeaders headers = inputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = contentType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        InputStream inputStream = inputMessage.getBody();
        String source = StreamUtils.copyToString(inputStream, charset);
        System.out.println("source:"+source);
        Order order = JSON.parseObject(source,Order.class);
        return order;
    }

    @Override
    public Order read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null,inputMessage);
    }
}
