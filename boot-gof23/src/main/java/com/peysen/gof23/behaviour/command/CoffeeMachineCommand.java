package com.peysen.gof23.behaviour.command;

import com.peysen.gof23.behaviour.mediator.IColleague;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 10:09
 * @Desc:
 */
public class CoffeeMachineCommand implements ICommand {
    private IColleague coffeeMachineColleague;

    public CoffeeMachineCommand(IColleague coffeeMachineColleague) {
        this.coffeeMachineColleague = coffeeMachineColleague;
    }

    @Override
    public void execute() {
        coffeeMachineColleague.open();
    }
}
