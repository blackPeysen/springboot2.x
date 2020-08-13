/**
 * @Author Peysen
 * @Date 2020/8/9 16:39
 * @Desc fanout类型，广播模式
 *      消息生产者直接将消息发送到fanout类型的交换器中，由交换器决定将消息发送到哪些消息队列中
 *      消息消费者将临时消息队列跟fanout类型的交换器进行绑定，从而从消息队列中获取消息进行处理
 */
package com.org.peysen.bootrabbitmq.testMq.fanout;