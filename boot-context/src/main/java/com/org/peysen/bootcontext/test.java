package com.org.peysen.bootcontext;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/1/17
 * @Desc :
 */
public class test {
    /**
     *  题1：请用JAVA模拟银行账户款项的存取功能。
     *      允许多个账户同时往同一个账户打钱，每个账户最多可存1000w元。
     *      允许多个端同时从同一个账户取钱，但不允许透支。
     */

    public static void main(String[] args) {
        Account account = new Account();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10 ,60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));

        //对应相同的账户，不同的人去存钱
        threadPoolExecutor.execute(new ATM2(800, account));
        threadPoolExecutor.execute(new ATM2(100, account));
        threadPoolExecutor.execute(new ATM2(200, account));

        // 对于相同的账户，不同的人去取钱
        threadPoolExecutor.execute(new ATM1(800,account));
        threadPoolExecutor.execute(new ATM1(50,account));
        threadPoolExecutor.execute(new ATM1(100,account));

        threadPoolExecutor.shutdown();
    }


    //取钱ATM
    static class ATM1 implements Runnable {
        private double drawMoney;
        private Account account;

        public ATM1(double drawMoney, Account account) {
            this.drawMoney = drawMoney;
            this.account = account;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在取钱。。。");
            account.draw(drawMoney);
        }
    }

    //存钱ATM
    static class ATM2 implements Runnable {
        private double drawMoney;
        private Account account;

        public ATM2(double drawMoney, Account account) {
            this.drawMoney = drawMoney;
            this.account = account;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "正在存钱。。。");
                account.save(drawMoney);
                System.out.println(Thread.currentThread().getName() + "存钱成功。。。");
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "存钱失败："+ e.getMessage());
            }
        }
    }

    //银行账户类
    static class Account{
        private Integer id;
        private String name;
        private double balance;
        private Date date;

        public Account() {
        }

        public Account(Integer id, String name, double balance, Date date) {
            this.id = id;
            this.name = name;
            this.balance = balance;
            this.date = date;
        }

        /**
         * 存钱
         */
        public synchronized void save(double saveMoney) throws Exception {
            //判断当前余额+所存金额之和是否超过账户最大金额
            double sum = balance + saveMoney ;

            //最大金额数1000w可做成常量或配置项
            if(sum > 1000d){
                throw new Exception("已超过账户所存最大金额。。。");
            }else{
                balance += saveMoney;
                System.out.println("此时账户余额为：" + balance);
            }
        }



        /**
         * 取钱
         *
         * 同步方法的同步监听器对象: 若该方法是非静态的那么就是this
         */
        public synchronized void draw(double drawMoney) {
            // 能取钱的操作函数
            if (balance >= drawMoney) {
                // 取出多少钱
                System.out.println(Thread.currentThread().getName() + "取出多少钱" + drawMoney);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 设置账户余额还有多少
                setBalance(balance - drawMoney);
                //  余额还有多少
                System.out.println(Thread.currentThread().getName() + "账户的余额是：" + getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() + "账户余额不足，余额为：" + getBalance());
            }
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", balance=" + balance +
                    ", date=" + date +
                    '}';
        }
    }

}
