/**
 * @Author Peysen
 * @Date 2020/8/9 17:21
 * @Desc routing类型，路由模式
 *      消息队列和交换器绑定时，需要指定路由键
 *      消息生产者将消息发送到routing类型的交换器时，也需要指定路由键，
 *              由交换器和指定的路由键决定将消息发送到哪些消息队列中。
 *      交换器不再把消息发送到所有跟它绑定的消息队列中，而是根据消息的路由键，
 *              判断队列的路由键与消息的路由键一致时，交换器才发送该消息到该队列中。
 *
 */
package com.org.peysen.bootrabbitmq.testMq.routing;