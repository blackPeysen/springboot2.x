package com.peysen.gof23.behaviour.mediator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 10:22
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        IMediator mediator = new SmartHomeMediator();
        IColleague alarm = new AlarmColleague("alarm", mediator);
        IColleague coffee = new CoffeeMachineColleague("coffee", mediator);

        mediator.getMessage(alarm, "闹钟响了。。");
    }
}
