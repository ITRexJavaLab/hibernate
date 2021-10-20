package com.itrex.java.lab.transition.repository.impl;

import static org.junit.Assert.assertFalse;

import com.itrex.java.lab.transition.entity.User;
import com.itrex.java.lab.transition.repository.BaseRepositoryTest;
import com.itrex.java.lab.transition.repository.UserRepository;
import java.util.List;

import org.junit.Test;

public class JDBCUserRepositoryImplTest extends BaseRepositoryTest {

    private final UserRepository repository;

    public JDBCUserRepositoryImplTest() {
        super();
        repository = new JDBCUserRepositoryImpl(getConnectionPool());
    }

    @Test
    public void selectAll_validData_shouldReturnExistUser() {
        //given && when
        final List<User> result = repository.selectAll();

        //then
        assertFalse(result.isEmpty());
    }

}