package com.org.peysen.bootcommon.thread;

import com.org.peysen.bootcommon.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/26
 * @Desc : 模拟多线程给10w用户发送短信祝福
 */
public class BatchThread {
    public static void main(String[] args) {
        //1、初始化数据
        List<UserEntity> entityList = initUser(11);
        //2、定义每个线程执行的数量
        int pageSize = 2;
        //3、计算每个线程执行的数据
        List<List<UserEntity>> lists = ListUtil.splitList(entityList, pageSize);
        //4、分批发送
        for(int i=0; i< lists.size();i++){
            SendThread sendThread = new SendThread(lists.get(i));
            Thread thread = new Thread(sendThread, "线程-"+(i+1));
            thread.start();
        }

    }

    static class SendThread implements Runnable{
        private List<UserEntity> list;

        public SendThread(List<UserEntity> list) {
            this.list = list;
        }

        @Override
        public void run() {
            if(list!=null && !list.isEmpty()){
                for(UserEntity userEntity : list){
                    System.out.println(Thread.currentThread().getName() + userEntity.toString());
                }
            }
            System.out.println();
        }
    }

    private static List<UserEntity> initUser(int count){
        List<UserEntity> entityList = new ArrayList<>(count);

        for (int i=0;i<count;i++) {
            UserEntity userEntity = new UserEntity("ID-"+(i+1), "Name-"+(i+1));
            entityList.add(userEntity);
        }
        return entityList;
    }
}
