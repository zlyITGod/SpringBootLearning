package com.zly.sonrediscache.service.impl;

import com.zly.sonrediscache.entity.User;
import com.zly.sonrediscache.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // 使用自定义的key生成策略
    // 缓存结果key：addUser::KeyGenerator:addUser
    @CachePut(value = "addUser",keyGenerator = "oneKeyGenerator")
    @Override
    public User addUser(User user) {
        user.setId(2);
        user.setName("cicad");
        return user;
    }
    // 缓存结果key：updateUser::2
    @CachePut(value = "updateUser",key = "#result.id")
    @Override
    public User updateUser(Integer id) {
        User user = new User() ;
        user.setId(id);
        user.setName("smile");
        return user;
    }

    // 缓存结果key: selectUser::3
    @Cacheable(cacheNames = "selectUser",key = "#id")
    @Override
    public User selectUser(Integer id) {
        User user = new User() ;
        user.setId(id);
        user.setName("cicadaSmile");
        return user;
    }
    // 删除指定key: selectUser::3
    @CacheEvict(value = "selectUser",key = "#id",beforeInvocation = true)
    @Override
    public void deleteUser(Integer id) {

    }
}
