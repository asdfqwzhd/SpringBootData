package com.springbootdata.test.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Period {

    @Field(type = FieldType.Date, format = DateFormat.year_month_day)
    private LocalDate startDay;

    @Field(type = FieldType.Date, format = DateFormat.year_month_day)
    private LocalDate endDay;
}
