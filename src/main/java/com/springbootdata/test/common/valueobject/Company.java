package com.springbootdata.test.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Field(type = FieldType.Keyword)
    private String companyCode;

    @Field(type = FieldType.Keyword)
    private String companyName;
}
