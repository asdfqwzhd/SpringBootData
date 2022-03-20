package com.springbootdata.test.elasticsearch.controller;

import com.springbootdata.test.elasticsearch.document.UserDocument;
import com.springbootdata.test.elasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * elasticsearch检索登录更新
 */
@Controller
@RequestMapping("/elasticsearch")
@RequiredArgsConstructor
public class UserDocumentController {

    private final UserRepository userRepository;

    /**
     * 全部用户取得（可指定翻页，顺序）
     * URL例：GET：http://localhost:8080/elasticsearch/user?page=2&sort=company.companyCode,company.departmentNo,asc&size=2
     * @param pageable 翻页信息
     * @return 用户列表
     */
    @GetMapping("/user")
    @ResponseBody
    public List<UserDocument> getAllUser(Pageable pageable){
        Page<UserDocument> userDocumentPage = this.userRepository.findAll(pageable);
        List<UserDocument> userDocuments = userDocumentPage.getContent();
        return userDocuments;
    }

    /**
     * 用户取得
     * URL例：GET：http://localhost:8080/elasticsearch/user/hj9999
     * @param userId 用户Id
     * @return 用户列表
     */
    @GetMapping("/user/{userId}")
    @ResponseBody
    public UserDocument getUserByUserId(@PathVariable(value = "userId") String userId){
        UserDocument userDocument = this.userRepository.findById(userId).get();
        return userDocument;
    }

    /**
     * 用户登录更新-单用户
     * URL例：POST：http://localhost:8080/elasticsearch/user/single
     * RequestBody：user-single-insert.json
     * @param user 用户信息
     * @return 成功
     */
    @PostMapping("/user/single")
    @ResponseBody
    public String saveSingleUser(@RequestBody UserDocument user){
        this.userRepository.save(user);
        return "SUCCESS";
    }

    /**
     * 用户登录更新-多用户
     * URL例：POST：http://localhost:8080/elasticsearch/user/multiple
     * RequestBody：user-multiple-insert.json
     * @param users 用户信息列表
     * @return 成功
     */
    @PostMapping("/user/multiple")
    @ResponseBody
    public String saveMultipleUser(@RequestBody List<UserDocument> users){
        this.userRepository.saveAll(users);
        return "SUCCESS";
    }
}
