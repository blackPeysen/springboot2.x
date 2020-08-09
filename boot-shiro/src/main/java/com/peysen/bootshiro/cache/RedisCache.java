package com.peysen.bootshiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/17 10:54
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private final String SHIRO_SESSION_PREFIX = "PEYSEN-CACHE:";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public V get(K k) throws CacheException {
        System.out.println("从redis中获取角色数据。。。");
        return (V)redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set(k,v,60, TimeUnit.SECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisTemplate.opsForValue().get(k);
        redisTemplate.delete(k);

        return v;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
