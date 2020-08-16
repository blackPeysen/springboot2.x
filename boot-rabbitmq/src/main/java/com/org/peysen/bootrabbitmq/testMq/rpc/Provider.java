package com.org.peysen.bootrabbitmq.testMq.rpc;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
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

        String queueName = "rpc";
        //此处注意：我们声明了要回复的队列。队列名称由RabbitMQ自动创建。
        String callbackQueueName = channel.queueDeclare().getQueue();
        final String corrID = UUID.randomUUID().toString();

        /**
         * 指定要发送的路由键和消息实体
         */
        String message = "4";
        /**
         * 信道发送消息到交换器上
         * 参数1：交换器的名称
         * 参数2：路由键
         * 参数3：额外的参数，是否持久化，
         * 参数4：发送的消息体
         */
        AMQP.BasicProperties props = new AMQP.BasicProperties
                                            .Builder()
                                            .replyTo(callbackQueueName)
                                            .correlationId(corrID)
                                            .build();
        channel.basicPublish("", queueName, props, message.getBytes());

        channel.basicConsume(callbackQueueName, true, new DefaultConsumer(channel) {
             //这是一个回调函数，客户端获取消息，就调用此方法，处理消息
             @Override
             public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                 if (corrID.equals(properties.getCorrelationId())) {
                     System.out.println("correlationID对应上的消息：" + new String(body));
                 } else {
                     System.out.println("correlationID未对应上的消息：" + new String(body));
                 }
             }
         });

        // 关闭资源
        // RabbitMQUtil.closeConn(connection, channel);
    }
}
