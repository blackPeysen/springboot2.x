package com.org.peysen.bootdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.peysen.bootdata.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/18 10:07
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
