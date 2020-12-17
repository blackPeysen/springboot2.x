package com.peysen.gof23.behaviour.mediator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:15
 * @Desc: 同事类接口
 *      每一个同事类都知道他的中介者对象
 *      每一个同事对象在需与其他同事通信的时候，都只与它的中介者通信
 */
public abstract class IColleague implements IElectrical{
    String name;
    IMediator mediator;

    public IColleague(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    abstract void sendMessage(String message);

    public String getColleagueName(){
        return this.name;
    }

}
