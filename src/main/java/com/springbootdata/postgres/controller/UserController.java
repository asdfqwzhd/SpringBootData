package com.springbootdata.postgres.controller;

import com.springbootdata.postgres.entity.UserEntity;
import com.springbootdata.common.valueobject.SystemItems;
import com.springbootdata.elasticsearch.document.UserDocument;
import com.springbootdata.elasticsearch.repository.UserDocumentRepository;
import com.springbootdata.postgres.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/postgres/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final UserDocumentRepository userDocumentRepository;

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
//        Company company = new Company("123","1243");
        return userEntity;
    }

    /**
     * 用户登录更新-单用户
     * URL例：POST：http://localhost:8080/elasticsearch/user/single
     * RequestBody：user-single-insert.json
     * @param user 用户信息
     * @return 成功
     */
    @PostMapping("/single")
    @ResponseBody
    public String saveSingleUser(@RequestBody UserEntity user){
        user.setSystemItems(new SystemItems(LocalDateTime.now(),LocalDateTime.now()));
        this.userMapper.save(user);
        UserDocument userDocument = new UserDocument(user.getUser().getUserId(), user.getUser(),user.getCompany(),user.getDepartment(),user.getValidPeriod(),user.getSystemItems());
        this.userDocumentRepository.save(userDocument);
        return "SUCCESS";
    }

    /**
     * 用户登录更新-多用户
     * URL例：POST：http://localhost:8080/elasticsearch/user/multiple
     * RequestBody：user-multiple-insert.json
     * @param users 用户信息列表
     * @return 成功
     */
    @PostMapping("/multiple")
    @ResponseBody
    public String saveMultipleUser(@RequestBody List<UserEntity> users){
        List<UserDocument> userDocuments = new ArrayList<>();
        users.forEach(user->{
            user.setSystemItems(new SystemItems(LocalDateTime.now(),LocalDateTime.now()));
            this.userMapper.save(user);
            userDocuments.add(new UserDocument(user.getUser().getUserId(), user.getUser(),user.getCompany(),user.getDepartment(),user.getValidPeriod(),user.getSystemItems()));
        });
        this.userDocumentRepository.saveAll(userDocuments);
        return "SUCCESS";
    }
}
