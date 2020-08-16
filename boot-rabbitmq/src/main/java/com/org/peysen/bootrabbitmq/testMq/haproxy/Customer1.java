package com.org.peysen.bootrabbitmq.testMq.haproxy;

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

        try {
            // 根据连接工厂生成一个连接
            Connection connection = RabbitMQUtil.getConnection(5670);
            // 根据连接生成一个通道
            Channel channel = connection.createChannel();

            /**
             * 消费者可以跟生产者一样，申明一个交换器；
             * 如果确定交换器已经存在，也可以直接对交换器进行绑定
             */
            channel.exchangeDeclare("cluster_exchange", BuiltinExchangeType.DIRECT);
            channel.queueDeclare("cluster_queue", false, false, true, null);

            /**
             * 将交换器与消息队列进行绑定
             */
            channel.queueBind("cluster_queue","cluster_exchange", "");

            System.out.println("Ready for testing...");
            channel.basicConsume("cluster_queue", true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("消费者[x] Received: '" + message + "'");
                }
            });
        }catch (Exception e){
            System.out.println("发生异常");
        }

//        while (true){
//
//        }

    }
}
