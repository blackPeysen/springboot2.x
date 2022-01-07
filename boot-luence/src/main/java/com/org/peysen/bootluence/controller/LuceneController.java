package com.org.peysen.bootluence.controller;

import com.org.peysen.bootluence.service.ILuceneManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:34
 * Desc:
 */
@RestController
public class LuceneController {

    @Autowired
    private ILuceneManagerService luenceService;

    @PostMapping("/createIndex")
    public String createIndex(){

        luenceService.createIndex(1619752L);

        return "success";
    }

}
