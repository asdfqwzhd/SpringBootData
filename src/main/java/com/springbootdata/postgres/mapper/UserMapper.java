package com.springbootdata.postgres.mapper;

import com.springbootdata.postgres.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserEntity sel(String id);

    void insert(UserEntity user);

    void save(UserEntity user);
}
