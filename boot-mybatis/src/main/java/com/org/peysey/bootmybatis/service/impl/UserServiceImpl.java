package com.org.peysey.bootmybatis.service.impl;

import com.org.peysey.bootmybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/18 10:12
 */

@Service
public class UserServiceImpl implements UserService {

    @Transactional
    public void testTransaction(){
        System.out.println("testTransaction");
    }
}
