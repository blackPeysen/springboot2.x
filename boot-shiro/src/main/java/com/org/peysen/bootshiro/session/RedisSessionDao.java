package com.org.peysen.bootshiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/17 11:31
 */

public class RedisSessionDao extends AbstractSessionDAO {
    private final String SHIRO_SESSION_PREFIX = "PEYSEN-SESSION:";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);

        System.out.println("doCreate sessionId:" + session.getId().toString());
        //保存到redis中
        redisTemplate.opsForValue().set(session.getId().toString(),session,60, TimeUnit.SECONDS);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("doReadSession...");

        if(sessionId == null){
            return null;
        }

        Object object = redisTemplate.opsForValue().get(sessionId.toString());

        Session session = (Session) object;

        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

        if (session != null && session.getId() != null){
            //更新到redis中
            redisTemplate.opsForValue().set(session.getId().toString(),session,60, TimeUnit.SECONDS);
        }
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null){
            //更新到redis中
            redisTemplate.opsForValue().decrement(session.getId().toString());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keys = redisTemplate.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = null;


        if (keys != null && keys.size() > 0){
            sessions = new HashSet<>();

            Session session = null;
            for(String key : keys){
                session = (Session) redisTemplate.opsForValue().get(key);
                sessions.add(session);
            }
        }

        return sessions;
    }
}
