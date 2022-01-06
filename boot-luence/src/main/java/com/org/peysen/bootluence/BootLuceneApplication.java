package com.org.peysen.bootluence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.org.peysen.bootluence.dao.mapper")
@SpringBootApplication
public class BootLuceneApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootLuceneApplication.class, args);
    }

}
