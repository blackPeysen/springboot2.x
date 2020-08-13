package com.org.peysen.bootrabbitmq.testMq.rpc;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 14:34
 * @Desc 消费者1，只接受error级别的日志消息
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 根据连接工厂生成一个连接
        Connection connection = RabbitMQUtil.getConnection();
        // 根据连接生成一个通道
        Channel channel = connection.createChannel();
        channel.queueDeclare("rpc", false, false, false, null);
        channel.basicConsume("rpc", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消费者:" + message);

                FibonacciRpcClient fibonacciRpc = new FibonacciRpcClient();
                String result = fibonacciRpc.call(message);
                System.out.println( "fib(4) is " + result);

                AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
                //我们在将要回复的消息属性中，放入从客户端传递过来的correlateId
                builder.correlationId(properties.getCorrelationId());
                AMQP.BasicProperties prop = builder.build();
                System.out.println("correlationId：" + properties.getCorrelationId());

                //发送给回复队列的消息，exchange=""，routingKey=回复队列名称
                //因为RabbitMQ对于队列，始终存在一个默认exchange=""，routingKey=队列名称的绑定关系
                channel.basicPublish("", properties.getReplyTo(), prop, (new String(body) + "-回复").getBytes());

                System.out.println("getReplyTo：" + properties.getReplyTo());
            }
        });
    }
}
