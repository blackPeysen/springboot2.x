package com.peysen.gof23.structural.facade;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 9:14
 * @Desc: 使用外观模式实现对家庭影院各个部件的控制
 */
public class Client {

    public static void main(String[] args) {
        DeviceFacade deviceFacade = new DeviceFacade();
        deviceFacade.openDevice();
        System.out.println("================");
        deviceFacade.closeDevice();
    }
}
