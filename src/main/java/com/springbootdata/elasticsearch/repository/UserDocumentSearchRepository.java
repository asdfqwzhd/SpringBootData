package com.springbootdata.elasticsearch.repository;

import com.springbootdata.elasticsearch.condition.UserDocumentCondition;
import com.springbootdata.elasticsearch.document.UserDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserDocumentSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchHits<UserDocument> findByQuery(UserDocumentCondition userDocumentCondition) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if (null != userDocumentCondition.getInsertDay()) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("systemItems.insertDateTime")
                    .gte(LocalDateTime.of(userDocumentCondition.getInsertDay(), LocalTime.MIN).format(DateTimeFormatter.ofPattern(DateFormat.date_hour_minute_second.getPattern())))
                    .lte(LocalDateTime.of(userDocumentCondition.getInsertDay(), LocalTime.MAX).format(DateTimeFormatter.ofPattern(DateFormat.date_hour_minute_second.getPattern()))));
        }

        if (Boolean.TRUE.equals(userDocumentCondition.getIsGivenUser())) {
            BoolQueryBuilder subBoolQueryBuilder = new BoolQueryBuilder();
            subBoolQueryBuilder.must(QueryBuilders.termQuery("company.companyCode","G3"))
                    .must(QueryBuilders.termQuery("department.departmentNo","QA01"))
                    .must(QueryBuilders.termQuery("user.userId","hj9997"));
            boolQueryBuilder.must(subBoolQueryBuilder);
        } else {
            if (StringUtils.hasText(userDocumentCondition.getCompanyCode())) {
                boolQueryBuilder.must(QueryBuilders.termQuery("company.companyCode",userDocumentCondition.getCompanyCode()));
            }
            if (StringUtils.hasText(userDocumentCondition.getDepartmentNo())) {
                boolQueryBuilder.must(QueryBuilders.termQuery("department.departmentNo",userDocumentCondition.getDepartmentNo()));
            }
            if (StringUtils.hasText(userDocumentCondition.getUserId())) {
                boolQueryBuilder.must(QueryBuilders.termQuery("user.userId",userDocumentCondition.getUserId()));
            }
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<UserDocument> searchHits = this.elasticsearchOperations.search(nativeSearchQuery,UserDocument.class);
        return searchHits;
    }
}
