package com.springbootdata.test.elasticsearch.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UserDocumentCondition {
    private LocalDate insertDay;
    private String userId;
    private String companyCode;
    private String departmentNo;
    private Boolean isGivenUser;
}
