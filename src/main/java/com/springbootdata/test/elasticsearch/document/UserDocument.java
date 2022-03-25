package com.springbootdata.test.elasticsearch.document;

import com.springbootdata.test.common.valueobject.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
@AllArgsConstructor
@Getter
public class UserDocument {

    @Id
    private String id;

    @Field(type = FieldType.Object)
    private User user;

    @Field(type = FieldType.Object)
    private Company company;

    @Field(type = FieldType.Object)
    private Department department;

    @Field(type = FieldType.Object)
    private Period validPeriod;

    @Field(type = FieldType.Object)
    private SystemItems systemItems;
}
