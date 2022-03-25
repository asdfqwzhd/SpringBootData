package com.springbootdata.test.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootdata.test.common.valueobject.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {

    private User user;

    private Company company;

    private Department department;

    private Period validPeriod;

    private SystemItems systemItems;

    @JsonIgnore
    private int count;

}
