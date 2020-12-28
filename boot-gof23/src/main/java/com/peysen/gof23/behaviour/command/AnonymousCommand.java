package com.peysen.gof23.behaviour.command;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/18 11:57
 * @Desc: 在一个方法中，声明一个内部类，该类可以使用该方法中的入参和局部变量
 */
public class AnonymousCommand {


    public void invoke(String name){
        class InnerClass{
            public void process(){
                System.out.println("inner-process:" + name);
            }
        }

        new InnerClass().process();
    }

    public static void main(String[] args) {
        new AnonymousCommand().invoke("peimengmeng");
    }

}
