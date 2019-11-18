package com.org.peysen.bootshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootShiroApplicationTests {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Test
    public void contextLoads() { }


    //使用simpleAccountRealm添加用户信息
    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("peysen","123456","admin");
    }


    //认证权限
    @Test
    public void testAuthentication(){
        //1、构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        //3、准备认证
        UsernamePasswordToken token = new UsernamePasswordToken("peysen", "123456");
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        subject.logout();
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        //检测是否具备指定角色
        subject.checkRole("admin");

    }

}
