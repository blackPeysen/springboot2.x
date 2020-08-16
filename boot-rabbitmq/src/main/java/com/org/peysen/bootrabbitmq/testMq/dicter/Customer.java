package com.org.peysen.bootrabbitmq.testMq.dicter;

import com.org.peysen.bootrabbitmq.config.RabbitConfig;
import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 14:34
 * @Desc 直连消费者
 *      在集群模式下，我们连接非数据节点时，判断是否可以消费消息
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 根据连接工厂生成一个连接
        Connection connection = RabbitMQUtil.getConnection();
        // 根据连接生成一个通道
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConfig.DIRECT_QUEUE1, true, false, false, null);
        channel.basicConsume(RabbitConfig.DIRECT_QUEUE1, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者:" + new String(body));
            }
        });

    }
}
