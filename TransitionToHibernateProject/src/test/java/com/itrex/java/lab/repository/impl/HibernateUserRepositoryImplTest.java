package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.entity.User;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import com.itrex.java.lab.repository.UserRepository;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class HibernateUserRepositoryImplTest extends BaseRepositoryTest {

    private final UserRepository repository;

    public HibernateUserRepositoryImplTest() {
        super();
        repository = new HibernateUserRepositoryImpl(getSessionFactory().openSession());
    }

    @Test
    public void selectAll_validHibernateData_shouldReturnExistUser() {
        //given && when
        User user = getSessionFactory().openSession().load(User.class, 1);
        final List<User> result = repository.selectAll();

        //then
        assertFalse(result.isEmpty());
    }

}