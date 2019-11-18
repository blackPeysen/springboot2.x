package com.org.peysen.bootshiro.test;

import com.org.peysen.bootshiro.BootShiroApplicationTests;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/16 15:33
 */
public class IniRealmTest extends BootShiroApplicationTests {


    //认证权限
    @Test
    public void testAuthentication(){
        //1、构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2、使用IniRealm
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        securityManager.setRealm(iniRealm);

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
