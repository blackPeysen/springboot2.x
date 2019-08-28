package com.org.peysen.bootdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/28 11:18
 */

@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/queryList")
    public List<Map<String,Object>>  queryList(){
        System.out.println("HelloController--》queryList");
        return jdbcTemplate.queryForList("select * from FAULT_CODE_CONFIG");
    }

}
