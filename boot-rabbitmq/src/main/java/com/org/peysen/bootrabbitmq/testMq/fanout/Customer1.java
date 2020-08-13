package com.org.peysen.bootrabbitmq.testMq.fanout;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 14:34
 * @Desc 消费者1
 */
public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 根据连接工厂生成一个连接
        Connection connection = RabbitMQUtil.getConnection();
        // 根据连接生成一个通道
        Channel channel = connection.createChannel();

        /**
         * 消费者声明一个临时消息队列，用来从交换器中获取消息
         */
        AMQP.Queue.DeclareOk queue = channel.queueDeclare();

        /**
         * 消费者可以跟生产者一样，申明一个交换器；
         * 如果确定交换器已经存在，也可以直接对交换器进行绑定
         */
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

        /**
         * 将交换器与消息队列进行绑定
         */
        channel.queueBind(queue.getQueue(),"logs", "");

        /**
         * 获取消息，进行处理
         */
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("消费者1： [x] Received '" + message + "'");
        };
        channel.basicConsume(queue.getQueue(), true, deliverCallback, consumerTag -> { });
    }
}
