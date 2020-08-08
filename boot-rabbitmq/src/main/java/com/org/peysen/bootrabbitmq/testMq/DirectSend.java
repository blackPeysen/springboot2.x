package com.org.peysen.bootrabbitmq.testMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/8
 * @Desc : 直连消息队列，发送消息
 */
public class DirectSend {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 设置连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("boot");
        connectionFactory.setPassword("boot");
        connectionFactory.setVirtualHost("boot-ems");

        // 根据连接工厂生成一个连接
        Connection connection = connectionFactory.newConnection();

        // 根据连接生成一个通道
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello", false, false, false, null);
        channel.basicPublish("", "hello", false, null, "hello RabbitMq".getBytes());

        channel.close();
        connection.close();

        System.out.println("直连hello队列，发布消息成功");
    }
}
