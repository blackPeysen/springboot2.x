package com.org.peysen.bootrabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 14:34
 * @Desc rabbitMQ连接工厂
 */
public class RabbitMQUtil {

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("boot");
        connectionFactory.setPassword("boot");
        connectionFactory.setVirtualHost("boot-ems");
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    public static void closeConn(Connection connection, Channel channel) throws IOException, TimeoutException {
        if (channel != null) channel.close();
        if (connection != null) connection.close();
    }

}
