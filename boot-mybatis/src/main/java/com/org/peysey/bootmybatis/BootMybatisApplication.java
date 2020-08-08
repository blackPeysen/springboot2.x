package com.org.peysey.bootmybatis;

import com.org.peysey.bootmybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan
@SpringBootApplication
public class BootMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootMybatisApplication.class, args);

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);

        System.out.println("userMapper ：" + userMapper.getClass());
    }

}
