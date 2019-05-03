package com.example.search;

import lombok.Data;

import java.util.Iterator;

@Data
public class ComparisonSearchCriteria extends SearchCriteria{

    private String key;
    private SearchOperation operation;
    private Object value;

    public ComparisonSearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
