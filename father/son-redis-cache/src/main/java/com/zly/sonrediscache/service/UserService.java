package com.zly.sonrediscache.service;

import com.zly.sonrediscache.entity.User;

public interface UserService {
    User addUser (User user) ;
    User updateUser (Integer id) ;
    User selectUser (Integer id) ;
    void deleteUser (Integer id);
}
