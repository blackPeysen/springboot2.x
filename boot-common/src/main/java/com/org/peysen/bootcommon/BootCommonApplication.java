package com.org.peysen.bootcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BootCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootCommonApplication.class, args);
    }

}
