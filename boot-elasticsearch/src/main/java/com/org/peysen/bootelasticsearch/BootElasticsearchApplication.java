package com.org.peysen.bootelasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@MapperScan(basePackages = "com.org.peysen.bootelasticsearch.dao")
@EnableElasticsearchRepositories
public class BootElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootElasticsearchApplication.class, args);
    }

}
