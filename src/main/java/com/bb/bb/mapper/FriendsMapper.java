package com.bb.bb.mapper;

import com.bb.bb.entity.Friends;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsMapper {
    int create(Friends friends);
    Friends[] list(Friends friends);
    void update(Friends friends);
}
