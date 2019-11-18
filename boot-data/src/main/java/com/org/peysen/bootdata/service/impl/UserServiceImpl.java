package com.org.peysen.bootdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.peysen.bootdata.dao.UserMapper;
import com.org.peysen.bootdata.entity.User;
import com.org.peysen.bootdata.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/18 10:12
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
