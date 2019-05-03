package com.example.search;

import lombok.Data;

import java.util.List;

@Data
public class LogicalSearchCriteria extends SearchCriteria{

    private List<SearchCriteria> childrens;

    private Operator operator;

    public enum Operator {
        AND, OR
    }

    public LogicalSearchCriteria(List<SearchCriteria> childrens, Operator operator) {
        this.childrens = childrens;
        this.operator = operator;
    }
}
