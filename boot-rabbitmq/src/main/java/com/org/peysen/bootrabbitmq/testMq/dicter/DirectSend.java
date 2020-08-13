package com.org.peysen.bootrabbitmq.testMq.dicter;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/8
 * @Desc : 直连消息队列，发送消息
 */
public class DirectSend {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 根据连接工厂生成一个连接
        Connection connection = RabbitMQUtil.getConnection();
        // 根据连接生成一个通道
        Channel channel = connection.createChannel();

        /**
         * 直接声明一个消息队列
         * 参数1：队列名称
         * 参数2：是否持久化队列
         * 参数3：是否独占
         * 参数4：当没有消费者与该队列连接时，是否自动删除
         * 参数5：额外参数
         */
        channel.queueDeclare("hello", false, false, false, null);

        /**
         * 向消息队列中发布消息
         */
        channel.basicPublish("", "hello", false, null, "hello RabbitMq".getBytes());

        RabbitMQUtil.closeConn(connection,channel);

        System.out.println("直连hello队列，发布消息成功");
    }
}
