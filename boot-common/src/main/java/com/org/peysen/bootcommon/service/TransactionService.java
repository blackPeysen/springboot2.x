package com.org.peysen.bootcommon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/15 16:38
 */
@Service
public class TransactionService {

    @Transactional
    public void helloTransaction(){
        System.out.println("helloTransaction....");
    }
}
