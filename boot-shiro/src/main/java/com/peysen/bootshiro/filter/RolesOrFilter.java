package com.peysen.bootshiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/17 11:13
 */
public class RolesOrFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("RolesOrFilter---isAccessAllowed");

        Subject subject = getSubject(servletRequest,servletResponse);
        String[] roles = (String[]) o;

        if (roles == null && roles.length == 0){
            return  true;
        }

        for(String role : roles){
            if (subject.hasRole(role)){
                return true;
            }
        }

        return false;
    }
}
