package com.org.peysen.bootrabbitmq.testMq.mirrorQueue;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/15 12:01
 * @Desc 在15672端口上声明一个镜像队列，然后再15673端口上查看是否存在该镜像队列
 */
public class Main1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * 声明一个镜像队列
         */
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("set_policy", "all");
        channel.queueDeclare("mirror-queue", false, false, true,arguments);

        channel.queueDeclare("common-queue", false, false, true,null);


        RabbitMQUtil.closeConn(connection, channel);
    }
}
