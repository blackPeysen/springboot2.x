package com.peysen.bootshiro.contoller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/16 17:02
 */

@RestController
public class LoginController {

    @RequiresPermissions("user:delete") //必须包含user:list权限
    @RequiresRoles("admin")           //必须包含admin角色
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            //设置是否记住我（不用二次登陆）
            token.setRememberMe(true);
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @RequestMapping(value = "/testRole", method = RequestMethod.POST)
    public String testRole() {
        return "testRole";
    }

    @RequestMapping(value = "/testRole1", method = RequestMethod.POST)
    public String testRole1() {
        return "testRole1";
    }

    @RequestMapping(value = "/testPerm", method = RequestMethod.POST)
    public String testPerm() {
        return "testPerm";
    }

    @RequestMapping(value = "/testPerm1", method = RequestMethod.POST)
    public String testPerm1() {
        return "testPerm1";
    }

}
