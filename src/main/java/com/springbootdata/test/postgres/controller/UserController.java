package com.springbootdata.test.postgres.controller;

import com.springbootdata.test.postgres.entity.UserEntity;
import com.springbootdata.test.postgres.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/postgres/user")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;


    /**
     * 用户取得
     * URL例：GET：http://localhost:8080/postgres/user/hj9999
     * @param userId 用户Id
     * @return 用户列表
     */
    @GetMapping("/{userId}")
    @ResponseBody
    public UserEntity getUserByUserId(@PathVariable(value = "userId") String userId) {
        UserEntity userEntity = this.userMapper.sel(userId);
        return userEntity;
    }
}
