package com.itrex.java.lab.repository;

import static com.itrex.java.lab.properties.Properties.H2_PASSWORD;
import static com.itrex.java.lab.properties.Properties.H2_URL;
import static com.itrex.java.lab.properties.Properties.H2_USER;

import com.itrex.java.lab.service.FlywayService;
import com.itrex.java.lab.util.HibernateUtil;
import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

public abstract class BaseRepositoryTest {

    private final FlywayService flywayService;
    private final JdbcConnectionPool connectionPool;
    private final SessionFactory sessionFactory;

    public BaseRepositoryTest () {
        flywayService = new FlywayService();
        connectionPool = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void initDB() {
        flywayService.migrate();
    }

    @After
    public void cleanDB() {
        flywayService.clean();
    }

    public JdbcConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
