package com.example.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {

    private User john;

    private User doe;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        john = new User();
        john.setAge(27);
        john.setEmail("dafu@gmail.com");
        john.setFirstName("No");
        john.setLastName("Way");
        userRepository.save(john);

        doe = new User();
        doe.setAge(22);
        doe.setEmail("daiko@gmail.com");
        doe.setFirstName("W");
        doe.setLastName("No");
        userRepository.save(doe);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void specification() {
        UserSpecificationBuilder userSpecificationBuilder = new UserSpecificationBuilder();
        ComparisonSearchCriteria spec2 = new ComparisonSearchCriteria("email", SearchOperation.LIKE, "%dafu%");
        ComparisonSearchCriteria spec3 = new ComparisonSearchCriteria("email", SearchOperation.LIKE, "%daiko%");
        LogicalSearchCriteria spec1 = new LogicalSearchCriteria(Arrays.asList(spec2, spec3), LogicalSearchCriteria.Operator.OR);

        List<User> userList = userRepository.findAll(userSpecificationBuilder.build(spec1));

        assertThat(john, isIn(userList));
        assertThat(doe, isIn(userList));
    }

    public void specification2() {
        UserSpecificationBuilder userSpecificationBuilder = new UserSpecificationBuilder();
        ComparisonSearchCriteria spec2 = new ComparisonSearchCriteria("email", SearchOperation.LIKE, "%dafu%");
        ComparisonSearchCriteria spec3 = new ComparisonSearchCriteria("email", SearchOperation.LIKE, "%daiko%");
        LogicalSearchCriteria spec1 = new LogicalSearchCriteria(Arrays.asList(spec2, spec3), LogicalSearchCriteria.Operator.AND);

        List<User> userList = userRepository.findAll(userSpecificationBuilder.build(spec1));

        assertThat(john, not(isIn(userList)));
        assertThat(doe, not(isIn(userList)));
    }
}
