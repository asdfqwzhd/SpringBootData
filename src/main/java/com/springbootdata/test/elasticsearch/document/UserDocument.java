package com.springbootdata.test.elasticsearch.document;

import com.springbootdata.test.elasticsearch.valueobject.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
@AllArgsConstructor
@Getter
public class UserDocument {

    @Field(type = FieldType.Keyword)
    @Id
    String userId;

    @Field(type = FieldType.Keyword)
    String userName;

    @Field(type = FieldType.Object)
    Company company;
}
