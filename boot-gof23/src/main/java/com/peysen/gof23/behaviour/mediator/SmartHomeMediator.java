package com.peysen.gof23.behaviour.mediator;

import com.peysen.gof23.behaviour.command.AlarmCommand;
import com.peysen.gof23.behaviour.command.CoffeeMachineCommand;
import com.peysen.gof23.behaviour.command.ICommand;

import java.util.*;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 9:22
 * @Desc: 智能家庭中介者
 */
public class SmartHomeMediator implements IMediator{
    private List<ICommand> commandList;
    private Map<String, IColleague> colleagueMap;

    public SmartHomeMediator() {
        this.commandList = new ArrayList<>(16);
        this.colleagueMap = new HashMap<>(16);
    }

    @Override
    public void registerColleague(IColleague colleague) {
        if (Objects.nonNull(colleague)){
            colleagueMap.put(colleague.getColleagueName(), colleague);
        }
    }

    @Override
    public void getMessage(IColleague colleague, String message) {
        if (colleague instanceof AlarmColleague){
            System.out.println("闹钟同事发来了信息：" + message);
            new CoffeeMachineCommand(colleagueMap.get("coffee")).execute();

        } else if (colleague instanceof CoffeeMachineColleague){
            System.out.println("咖啡机同事发来了信息：" + message);
            new AlarmCommand(colleagueMap.get("alarm")).execute();

        } else {
            System.out.println("暂无该同事处理....");
        }
    }
}
