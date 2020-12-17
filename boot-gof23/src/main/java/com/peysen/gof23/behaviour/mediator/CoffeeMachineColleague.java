package com.peysen.gof23.behaviour.mediator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:20
 * @Desc: 咖啡机
 */
public class CoffeeMachineColleague extends IColleague{

    public CoffeeMachineColleague(String name, IMediator mediator) {
        super(name, mediator);
        super.mediator.registerColleague(this);
    }

    @Override
    public void sendMessage(String message) {
        this.mediator.getMessage(this, message);
    }

    @Override
    public void open() {
        System.out.println("打开咖啡机");
    }

    @Override
    public void close() {
        System.out.println("关闭咖啡机");
    }
}
