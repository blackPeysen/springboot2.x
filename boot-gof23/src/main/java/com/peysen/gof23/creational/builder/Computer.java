package com.peysen.gof23.creational.builder;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:31
 * @Desc:
 */
public class Computer {
    private final String cpu; //必须
    private final String ram; //必须
    private final int usbCount; //可选
    private final String keyboard; //可选
    private final String display; //可选

    public Computer(ComputerBuilber computerBuilder) {
        this.cpu = computerBuilder.getCpu();
        this.ram = computerBuilder.getRam();
        this.usbCount = computerBuilder.getUsbCount();
        this.keyboard = computerBuilder.getKeyboard();
        this.display = computerBuilder.getDisplay();
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", usbCount=" + usbCount +
                ", keyboard='" + keyboard + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
