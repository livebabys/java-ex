package com.bb.bb.service;

import com.bb.bb.entity.User;
import com.bb.bb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User find(int id){
      return  userMapper.find(id);
    }

    public int create(String username,String password,String nickname){
        return userMapper.create(username,password,nickname);
    }
}
