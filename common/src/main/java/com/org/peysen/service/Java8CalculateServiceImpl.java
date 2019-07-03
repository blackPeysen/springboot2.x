package com.org.peysen.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @Description: java8 使用Lambda循环求和
 * @Author: peimm
 * @CreateDate: 2019/7/3 07:24
 * @UpdateRemark: The modified content
 */

@Profile("java8")
@Service
public class Java8CalculateServiceImpl implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("Java8CalculateServiceImpl");
        int sum=Stream.of(values).reduce(0,Integer::sum);

        return sum;
    }
}
