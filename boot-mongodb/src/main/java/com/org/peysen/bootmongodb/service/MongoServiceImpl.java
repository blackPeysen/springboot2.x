package com.org.peysen.bootmongodb.service;

import com.org.peysen.bootmongodb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @Auther: peimengmeng
 * @Date: 2021/11/25_07:48
 * @Desc:
 */
@Service
public class MongoServiceImpl {
    @Autowired
    private MongoTemplate mongoTemplate;


    public long count(){
        return mongoTemplate.count(new Query(), "Users");
    }

    public void save(){
        User user = new User();
        user.setName("peysen");
        user.setAge(18);

        mongoTemplate.save(user, "Users");
    }
}
