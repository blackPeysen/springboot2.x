package com.org.peysen.bootcommon.controller;

import com.org.peysen.bootcommon.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/15 16:39
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/helloTransaction")
    public String helloTransaction(){
        transactionService.helloTransaction();

        return "success";
    }

}
