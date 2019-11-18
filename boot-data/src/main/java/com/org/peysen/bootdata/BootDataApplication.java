package com.org.peysen.bootdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.org.peysen.bootdata.dao.*")
public class BootDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootDataApplication.class, args);
    }

}
