package com.itrex.java.lab.transition;

import com.itrex.java.lab.transition.entity.User;
import com.itrex.java.lab.transition.repository.UserRepository;
import com.itrex.java.lab.transition.repository.impl.HibernateUserRepositoryImpl;
import com.itrex.java.lab.transition.service.FlywayService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itrex.java.lab.transition.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {

    public static void main(String[] args) {
        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

//        System.out.println("============CREATE CONNECTION POOL================");
//        JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);

        System.out.println("=============CREATE UserRepository================");
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository = new HibernateUserRepositoryImpl(session);

        List<User> users = userRepository.selectAll();
        System.out.println("Step 1 select all users:" + users);

        User firstAddedUser = new User();
        firstAddedUser.setName("some name");
        firstAddedUser.setEmail("some email");
        firstAddedUser.setDateOfBirth(Timestamp.valueOf(LocalDateTime.now()));
        userRepository.add(firstAddedUser);
        System.out.println("\nStep 2 add user:" + firstAddedUser);

        users = userRepository.selectAll();
        System.out.println("\nStep 3 select all users:" + users);

        List<User> newUsers = new ArrayList<>();

        User user = new User();
        user.setName("some name");
        user.setEmail("some email 2");
        user.setDateOfBirth(Timestamp.valueOf(LocalDateTime.now()));
        newUsers.add(user);

        User user2 = new User();
        user2.setName("some name");
        user2.setEmail("some email 3");
        user2.setDateOfBirth(Timestamp.valueOf(LocalDateTime.now()));
        newUsers.add(user2);


        Session session2 = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository2 = new HibernateUserRepositoryImpl(session2);
        Transaction transaction = null;
        try {
            transaction = session2.beginTransaction();
            userRepository2.addAll(newUsers);
            System.out.println("\nStep 4 add all users:" + newUsers);

            users = userRepository2.selectAll();
            System.out.println("\nStep 5.1 select all users:" + users);

            users = userRepository.selectAll();
            System.out.println("\nStep 5.2 select all users:" + users);

            transaction.commit();

            users = userRepository.selectAll();
            System.out.println("\nStep 5.3 select all users:" + users);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e.getMessage());
        }

        System.out.println("\nStep 6 count all users: " + userRepository.count());

        System.out.println("=========CLOSE ALL UNUSED CONNECTIONS=============");
//        jdbcConnectionPool.dispose();
        HibernateUtil.shutdown();
        System.out.println("=================SHUT DOWN APP====================");
    }

}
