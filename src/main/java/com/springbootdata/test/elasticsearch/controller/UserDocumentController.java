package com.springbootdata.test.elasticsearch.controller;

import com.springbootdata.test.elasticsearch.condition.UserDocumentCondition;
import com.springbootdata.test.elasticsearch.document.UserDocument;
import com.springbootdata.test.elasticsearch.repository.UserDocumentRepository;
import com.springbootdata.test.elasticsearch.repository.UserDocumentSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * elasticsearch检索登录更新
 */
@Controller
@RequestMapping("/elasticsearch/user")
@RequiredArgsConstructor
public class UserDocumentController {

    private final UserDocumentRepository userDocumentRepository;

    private final UserDocumentSearchRepository userDocumentSearchRepository;

    /**
     * 全部用户取得（可指定翻页，顺序）
     * URL例：GET：http://localhost:8080/elasticsearch/user?page=2&sort=company.companyCode,company.departmentNo,asc&size=2
     * @param pageable 翻页信息
     * @return 用户列表
     */
    @ResponseBody
    public List<UserDocument> getAllUser(Pageable pageable){
        Page<UserDocument> userDocumentPage = this.userDocumentRepository.findAll(pageable);
        List<UserDocument> userDocuments = userDocumentPage.getContent();
        return userDocuments;
    }

    /**
     * 用户取得
     * URL例：GET：http://localhost:8080/elasticsearch/user/hj9999
     * @param userId 用户Id
     * @return 用户列表
     */
    @GetMapping("/{userId}")
    @ResponseBody
    public UserDocument getUserByUserId(@PathVariable(value = "userId") String userId){
        UserDocument userDocument = this.userDocumentRepository.findById(userId).get();
        return userDocument;
    }

    /**
     * 用户取得
     * URL例：GET：http://localhost:8080/elasticsearch/user/hj9999
     * @return 用户列表
     */
    @GetMapping("/by-query")
    @ResponseBody
    public List<UserDocument> getUserByQuery(@RequestParam(value="insertDay", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate insertDay,
                                             @RequestParam(value="userId", required = false) String userId,
                                             @RequestParam(value="companyCode", required = false) String companyCode,
                                             @RequestParam(value="departmentNo", required = false) String departmentNo,
                                             @RequestParam(value="isGivenUser", required = false) Boolean isGivenUser){
        UserDocumentCondition userDocumentCondition = new UserDocumentCondition();
        userDocumentCondition.setInsertDay(insertDay);
        userDocumentCondition.setUserId(userId);
        userDocumentCondition.setCompanyCode(companyCode);
        userDocumentCondition.setDepartmentNo(departmentNo);
        userDocumentCondition.setIsGivenUser(isGivenUser);
        SearchHits<UserDocument> searchHits = this.userDocumentSearchRepository.findByQuery(userDocumentCondition);
        return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
