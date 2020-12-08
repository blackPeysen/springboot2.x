package com.peysen.gof23.creational.builder;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:31
 * @Desc:
 */
public class ComputerBuilber {
    private  String cpu; //必须
    private  String ram; //必须
    private  int usbCount; //可选
    private  String keyboard; //可选
    private  String display; //可选

    public ComputerBuilber(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public ComputerBuilber buildUsbCount(int usbCount){
        this.usbCount = usbCount;
        return this;
    }

    public ComputerBuilber buildkeyboard(String keyboard){
        this.keyboard = keyboard;
        return this;
    }

    public ComputerBuilber buildDisplay(String display){
        this.display = display;
        return this;
    }

    public  Computer builder(){
        return new Computer(this);
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setUsbCount(int usbCount) {
        this.usbCount = usbCount;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getUsbCount() {
        return usbCount;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public String getDisplay() {
        return display;
    }
}
