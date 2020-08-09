package com.peysen.bootshiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/16 16:59
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUsername(username);
        //从数据库或者缓存中获取权限数据
        Set<String> permissions = getPermissions(username);

        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    private Set<String> getPermissions(String userName){
        Set<String> sets = new HashSet<>();
        sets.add("user:delete");
        sets.add("user:update");
        return sets;
    }

    private Set<String> getRolesByUsername(String userName){
        System.out.println("模拟从数据库中获取角色数据。。。");
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }



    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        //从主体传过来的认证信息中获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //从主体传过来的认证信息中获取认证密码
        String userPwd = new String((char[]) authenticationToken.getCredentials());

        //根据用户名从数据库获取密码
        String password = "2415b95d3203ac901e287b76fcef640b";

        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(userPwd)) {
            throw new AccountException("密码不正确");
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(userName, password,
                ByteSource.Util.bytes(userName + "salt"), getName());
    }

    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }
}
