package com.org.peysen.bootrabbitmq.testMq.fanout;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 16:41
 * @Desc 生产者
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 声明一个交换器
         * 参数1：交换器的名称
         * 参数2：交换器的类型，固定为fanout类型
         */
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

        /**
         * 信道发送消息到交换器上
         * 参数1：交换器的名称
         * 参数2：路由键，在fanout类型中无用
         * 参数3：额外的参数，是否持久化，
         * 参数4：发送的消息体
         */
        String message = "rabbitMQ fanout type message";
        channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        RabbitMQUtil.closeConn(connection, channel);
    }
}
