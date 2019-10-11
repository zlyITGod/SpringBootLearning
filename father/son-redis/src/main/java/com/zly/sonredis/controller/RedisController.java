package com.zly.sonredis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate ;

    @RequestMapping("/setGet")
    public String setGet() {
        stringRedisTemplate.opsForValue().set("first","java");
        return stringRedisTemplate.opsForValue().get("first");
    }

    @Resource
    private RedisTemplate redisTemplate ;

    //设置key的有效期10秒
    @RequestMapping("/setKey")
    public String setKeyTime (){
        redisTemplate.opsForValue().set("sencond","javaee",10, TimeUnit.SECONDS);
        return "success";
    }

    @RequestMapping("/getKey")
    public String getTimeKey (){
        // 这里 Key 过期后，返回的是字符串 'null'
        return String.valueOf(redisTemplate.opsForValue().get("sencond")) ;
    }

}
