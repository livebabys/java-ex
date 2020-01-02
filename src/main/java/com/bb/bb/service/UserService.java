package com.bb.bb.service;

import com.bb.bb.entity.User;
import com.bb.bb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User find(User user){
      return  userMapper.find(user);
    }

    public int create(User user){
        return userMapper.create(user);
    }

    public void update(User user){  userMapper.update(user);}
}
