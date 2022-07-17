package com.microservice.demo.elastic.model.index.impl;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "employee-index")
public class EmployeeModel {
    @Id
    private Long id;
    private String name;
    private int age;
    private String position;
}
