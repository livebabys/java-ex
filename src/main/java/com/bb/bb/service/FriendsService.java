package com.bb.bb.service;

import com.bb.bb.entity.Friends;
import com.bb.bb.mapper.FriendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsService {
    @Autowired
    FriendsMapper friendsMapper;

    public Friends[] list(Friends friends){
       return friendsMapper.list(friends);
    }

}
