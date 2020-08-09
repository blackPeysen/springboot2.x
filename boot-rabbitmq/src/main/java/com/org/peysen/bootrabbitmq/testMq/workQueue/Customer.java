package com.org.peysen.bootrabbitmq.testMq.workQueue;

import com.org.peysen.bootrabbitmq.util.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Peysen
 * @Date 2020/8/9 14:34
 * @Desc 消费者，开启多个线程同时对消息队列进行消费
 *          默认是轮训，每个消费者都获取相等数量的信息进行消费
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new AckWorker("消费者"  + i));
            thread.start();
        }
    }

    /**
     * 自动确认worker
     */
    static class Worker implements Runnable{

        private String threadName;

        public Worker(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            // 根据连接工厂生成一个连接
            Connection connection = null;
            try {
                connection = RabbitMQUtil.getConnection();
                // 根据连接生成一个通道
                Channel channel = connection.createChannel();

                channel.queueDeclare("hello", false, false, false, null);
                /**
                 * 参数二autoAck：是否自动向Rabbitmq队列确认消息已经被消费
                 *      如果true：自动确认，一次性拿到所有分配到该消费者的消息列表，如果在处理到N个消息时，服务坏了，就会导致消息丢失
                 */
                channel.basicConsume("hello", true, new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(threadName + ":"+ new String(body));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 自动确认worker
     */
    static class AckWorker implements Runnable{

        private String threadName;

        public AckWorker(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            // 根据连接工厂生成一个连接
            Connection connection = null;
            try {
                connection = RabbitMQUtil.getConnection();
                // 根据连接生成一个通道
                Channel channel = connection.createChannel();

                //  设置通道每次只能消费一条消息
                channel.basicQos(1);
                channel.queueDeclare("hello", false, false, false, null);
                /**
                 * 参数二autoAck：是否自动向Rabbitmq队列确认消息已经被消费
                 *      如果true：自动确认，一次性拿到所有分配到该消费者的消息列表，如果在处理到N个消息时，服务坏了，就会导致消息丢失
                 */
                channel.basicConsume("hello", false, new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(threadName + ":"+ new String(body));

                        /**
                         * 在处理完消息后手动的进行消息确认
                         * 参数1：需要确认的消息ID
                         * 参数2：是否开启多个消息同时确认
                         */
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

}
