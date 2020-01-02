package com.bb.bb.mapper;

import com.bb.bb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User find(User user);
    int create(User user);
    void update(User user);
}
