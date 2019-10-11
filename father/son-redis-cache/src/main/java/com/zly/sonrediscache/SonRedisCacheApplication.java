package com.zly.sonrediscache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = {"com.zly.sonrediscache.mapper"})
public class SonRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SonRedisCacheApplication.class, args);
    }

}
