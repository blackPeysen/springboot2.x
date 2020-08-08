package com.org.peysen.bootdata.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: mybatis-plus 插件配置信息
 * Created by mengmeng.Pei
 * 2019/9/18 10:14
 */
@Configuration
@MapperScan("com.org.peysen.bootdata.dao.*")
public class MybatisPlusConfiguration {
    /*
     * 分页插件,自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
