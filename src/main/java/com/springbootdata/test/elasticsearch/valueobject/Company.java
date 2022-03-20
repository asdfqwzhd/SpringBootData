package com.springbootdata.test.elasticsearch.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@Getter
public class Company {

    @Field(type = FieldType.Keyword)
    String companyCode;

    @Field(type = FieldType.Keyword)
    String departmentNo;

    @Field(type = FieldType.Keyword)
    String companyName;
}
