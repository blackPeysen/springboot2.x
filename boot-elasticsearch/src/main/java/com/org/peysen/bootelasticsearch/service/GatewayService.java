package com.org.peysen.bootelasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * Author: peimengmeng
 * Date: 2022/1/18 16:12
 * Desc:
 *  查询某时间段内，调用过API接口的商户appKey有哪些，及对应的调用总次数。
 *  页面展示时，分页查询，总条数。
 */

@Service
public class GatewayService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void search(){
        boolean b = elasticsearchTemplate.indexExists("get-together");

        System.out.println(b);
    }
}
