package com.org.peysen.bootrabbitmq.producer;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/7 18:59
 * @UpdateRemark: 发布者接口
 */
public interface Sender<T> {

    void sender(T t);
}
