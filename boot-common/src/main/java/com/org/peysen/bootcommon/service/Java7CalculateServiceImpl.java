package com.org.peysen.bootcommon.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:24
 * @UpdateRemark: The modified content
 */

@Profile("java7")
@Service
public class Java7CalculateServiceImpl implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("Java7CalculateServiceImpl");
        int sum=0;

        for (int i=0;i<values.length;i++){
            sum+=values[i];
        }

        return sum;
    }

}
