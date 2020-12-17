package com.peysen.gof23.behaviour.mediator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:14
 * @Desc: 中介者接口
 */
public interface IMediator {

    void registerColleague(IColleague colleague);

    /**
     * 核心方法：
     *      获取同事发来的信息，并进行处理
     *      判断是哪个同事，根据不同的同事身份，做对应的逻辑处理
     */
    void getMessage(IColleague colleague, String message);

}
