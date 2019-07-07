package com.org.peysen.rabbitmq.producer;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 18:59
 * @UpdateRemark: The modified content
 */
public interface Sender<T> {

    void sender(T t);
}
