package com.peysen.gof23.behaviour.mediator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:16
 * @Desc: 闹钟
 */
public class AlarmColleague extends IColleague{

    public AlarmColleague(String name, IMediator mediator) {
        super(name, mediator);
        super.mediator.registerColleague(this);
    }



    @Override
    public void sendMessage(String message) {
        super.mediator.getMessage(this, message);
    }

    @Override
    public void open() {
        System.out.println("打开闹钟");
    }

    @Override
    public void close() {
        System.out.println("关闭闹钟");
    }
}
