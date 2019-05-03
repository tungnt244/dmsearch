package com.example.search;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {

    public Specification<User> build(LogicalSearchCriteria logicalNode) {
        List<Specification<User>> specs = logicalNode.getChildrens().stream().map(this::build
        ).collect(Collectors.toList());
        Specification<User> result = specs.get(0);
        switch (logicalNode.getOperator()) {
            case OR:
                for (int i = 1; i < specs.size(); i++) {
                    result = Specification.where(result).or(specs.get(i));
                }
                break;
            case AND:
                for (int i = 1; i < specs.size(); i++) {
                    result = Specification.where(result).and(specs.get(i));
                }
        }
        return result;
    }

    public Specification<User> build(ComparisonSearchCriteria compareNode) {
        return new UserSpecification(compareNode);
    }

    public Specification<User> build(SearchCriteria compareNode) {
        if (compareNode instanceof ComparisonSearchCriteria) {
            return build((ComparisonSearchCriteria) compareNode);
        } else if (compareNode instanceof LogicalSearchCriteria) {
            return build((LogicalSearchCriteria) compareNode);
        }
        return null;
    }
}
