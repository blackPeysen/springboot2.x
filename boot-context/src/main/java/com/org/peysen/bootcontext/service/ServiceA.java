package com.org.peysen.bootcontext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/2
 * @Desc :
 */
@Service
public class ServiceA {
    @Autowired
    private ServiceB serviceB;

    public ServiceB getServiceB() {
        return serviceB;
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
