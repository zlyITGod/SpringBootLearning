package com.zly.sonredis.controller;

import com.zly.sonredis.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@RestController
public class SerializeController {

    @Resource
    private RedisTemplate redisTemplate ;

    @RequestMapping("/setUser")
    public String setUser (){
        User user = new User() ;
        user.setName("cicada");
        user.setAge(22);
        List<String> list = new ArrayList<>() ;
        list.add("小学");
        list.add("初中");
        list.add("高中");
        list.add("大学");
        user.setEducation(list);
        redisTemplate.opsForValue().set("userInfo",user);
        return "success" ;
    }

    @RequestMapping("/getUser")
    public User getUser (){
        return (User)redisTemplate.opsForValue().get("userInfo") ;
    }
}
