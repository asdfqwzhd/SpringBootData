package com.springbootdata.test.postgres.mapper;

import com.springbootdata.test.postgres.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserEntity sel(String id);
}
