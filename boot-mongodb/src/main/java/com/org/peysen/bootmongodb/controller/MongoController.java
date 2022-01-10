package com.org.peysen.bootmongodb.controller;

import com.org.peysen.bootmongodb.service.impl.MongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: peimengmeng
 * @Date: 2021/11/25_07:47
 * @Desc:
 */
@RestController(value = "/mongo")
public class MongoController {
    @Autowired
    private MongoServiceImpl mongoService;

    @GetMapping("/count")
    public long count(){
        return mongoService.count();
    }

    @PostMapping("/save")
    public String save(){
        mongoService.save();
        return "success";
    }

}
