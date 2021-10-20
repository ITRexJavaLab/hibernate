package com.itrex.java.lab.transition.repository;

import com.itrex.java.lab.transition.entity.User;
import java.util.List;

public interface UserRepository {

    List<User> selectAll();
    void add(User user);
    void addAll(List<User> users);
    Long count();
}
