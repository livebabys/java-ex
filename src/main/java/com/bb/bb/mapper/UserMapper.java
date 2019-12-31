package com.bb.bb.mapper;

import com.bb.bb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User find(int id);
    int create(User user);
}
