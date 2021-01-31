package com.org.peysen.bootzk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootZkApplication {

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootZkApplication.class, args);

        ZooKeeper zooKeeper = applicationContext.getBean(ZooKeeper.class);
        System.out.println(zooKeeper.getState());

        zooKeeper.create("/test", "pmm".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        applicationContext.close();
    }

}
