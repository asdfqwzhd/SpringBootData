package com.springbootdata.test.elasticsearch.repository;

import com.springbootdata.test.elasticsearch.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, String> {
}