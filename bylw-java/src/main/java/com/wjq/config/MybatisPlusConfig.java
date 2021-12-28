package com.wjq.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author wjq
 * @date 2021/12/12 16:32
 **/

@Configuration
@EnableTransactionManagement
@MapperScan("com.wjq.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return  new PaginationInterceptor();
    }

//    @Bean
//    public OptimisticLockerInnerInterceptor mybatisPlusInterceptor() {
//
//        return new OptimisticLockerInnerInterceptor();
//    }

    @Bean
    @Profile({"dev","test"})
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        PerformanceMonitorInterceptor interceptor = new PerformanceMonitorInterceptor();
        interceptor.setUseDynamicLogger(true);
        return new PerformanceMonitorInterceptor();
    }


}
