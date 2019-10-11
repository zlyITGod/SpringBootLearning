package com.zly.sonrediscache;

import com.zly.sonrediscache.entity.Employee;
import com.zly.sonrediscache.entity.User;
import com.zly.sonrediscache.mapper.EmployeeMapper;
import com.zly.sonrediscache.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SonRedisCacheApplication.class)
public class SonRedisCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;   //操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;            //k-v都是对象

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void test01() {
        //给redis中保存数据
//    stringRedisTemplate.opsForValue().append("msg", "hello");
//    String msg = stringRedisTemplate.opsForValue().get("msg");
//    System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    @Test
    public void test02() {
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
//    redisTemplate.opsForValue().set("emp-01", empById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
        empRedisTemplate.opsForValue().set("emp-01", empById);
        System.out.println(empById);
    }




    @Resource
    private UserService userService ;
    // 分别测试：增、改、查、删,四个方法
    @Test
    public void testAdd (){
        User user = new User() ;
        user.setId(1);
        user.setName("cicada");
        userService.addUser(user) ;
    }
    @Test
    public void testUpdate (){
        userService.updateUser(2) ;
    }
    @Test
    public void testSelect (){
        userService.selectUser(2) ;
    }
    @Test
    public void testDelete (){
        userService.deleteUser(2) ;
    }

}
