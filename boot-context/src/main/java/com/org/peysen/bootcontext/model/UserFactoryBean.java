package com.org.peysen.bootcontext.model;

import com.org.peysen.bootcontext.entity.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/4
 * @Desc :
 */
@Component
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user =new User();
        user.setAge(19);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
