package com.springbootdata.elasticsearch.repository;

import com.springbootdata.elasticsearch.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, String> {
}