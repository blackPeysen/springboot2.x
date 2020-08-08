package com.org.peysen.bootcontext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/8/2
 * @Desc :
 */
@Service
public class ServiceB {

    @Autowired
    private ServiceA serviceA;

    public ServiceA getServiceA() {
        return serviceA;
    }

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
