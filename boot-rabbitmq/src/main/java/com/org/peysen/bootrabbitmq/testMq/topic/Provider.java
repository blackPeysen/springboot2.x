package com.org.peysen.bootrabbitmq.testMq.topic;

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

        String exchangeName = "topics";

        /**
         * 声明一个交换器
         * 参数1：交换器的名称
         * 参数2：交换器的类型，为direct基于route路由模式
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);

        /**
         * 指定路由键
         *      一般以.分割
         *      *匹配一个单词
         *      #匹配多个单词
         */
        String routeKey = "user.save.delte";

        /**
         * 消息实体
         */
        String message = "rabbitMQ route key type :【" + routeKey + "】 message";

        /**
         * 信道发送消息到交换器上
         * 参数1：交换器的名称
         * 参数2：路由键
         * 参数3：额外的参数，是否持久化，
         * 参数4：发送的消息体
         */
        channel.basicPublish(exchangeName, routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        // 关闭资源
        RabbitMQUtil.closeConn(connection, channel);
    }
}
