package com.org.peysen.bootreactor.dataLoader;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 堵塞式数据加载
 * Created by mengmeng.Pei
 * 2019/8/28 18:02
 */
public class DataLoader {

    public final  void load(){
        long startTime = System.currentTimeMillis();//开始时间
        doLoad(); //具体执行
        long costTime = System.currentTimeMillis() - startTime; //消耗时间
        System.out.println("load()总耗时：" + costTime +"ms");
    }

    protected void doLoad(){
        loadConfiguration();
        loadUsers();
        loadOrders();
    }

    protected final void loadConfiguration(){
        loadMock("loadConfiguration",1);
    }

    protected final void loadUsers(){
        loadMock("loadUsers",2);
    }

    protected final void loadOrders(){
        loadMock("loadOrders",3);
    }

    protected final void loadMock(String source, int second){
        try{
            long startTime = System.currentTimeMillis();
            long toMillis = TimeUnit.SECONDS.toMillis(second);
            Thread.sleep(toMillis);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程:%s] %s 耗时 : %d 毫秒 \n", Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new DataLoader().load();
    }

}
