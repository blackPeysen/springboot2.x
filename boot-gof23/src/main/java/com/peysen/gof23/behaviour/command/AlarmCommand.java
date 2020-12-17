package com.peysen.gof23.behaviour.command;

import com.peysen.gof23.behaviour.mediator.IColleague;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:41
 * @Desc:
 */
public class AlarmCommand implements ICommand{
    private IColleague alarmColleague;

    public AlarmCommand(IColleague alarmColleague) {
        this.alarmColleague = alarmColleague;
    }

    @Override
    public void execute() {
        alarmColleague.open();
    }
}
