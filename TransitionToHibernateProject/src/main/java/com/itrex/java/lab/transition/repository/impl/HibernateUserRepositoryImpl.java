package com.itrex.java.lab.transition.repository.impl;

import com.itrex.java.lab.transition.entity.User;
import com.itrex.java.lab.transition.repository.UserRepository;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {

    private final Session session;

    public HibernateUserRepositoryImpl(Session session) {
        this.session = session;
    }

    public List<User> selectAll() {
        return session.createQuery("from User u", User.class).list();
    }

    public void add(User user) {
        session.save(user);
    }

    public void addAll(List<User> users) {
        for (User user : users) {
            session.save(user);
        }
    }

    public Long count() {
        BigInteger count = (BigInteger)session.createSQLQuery("select count(*) from user").uniqueResult();
        return count.longValue();
    }
}
