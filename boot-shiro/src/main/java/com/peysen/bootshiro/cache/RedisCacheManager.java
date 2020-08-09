package com.peysen.bootshiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 缓存 权限数据和角色数据
 * Created by mengmeng.Pei
 * 2019/9/17 10:54
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("RedisCacheManager  getCache");
        return redisCache;
    }
}