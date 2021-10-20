package com.itrex.java.lab.transition.repository;

import com.itrex.java.lab.transition.service.FlywayService;
import com.itrex.java.lab.transition.properties.Properties;
import com.itrex.java.lab.transition.util.HibernateUtil;
import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;

public abstract class BaseRepositoryTest {

    private final FlywayService flywayService;
    private final JdbcConnectionPool connectionPool;
    private final SessionFactory sessionFactory;

    public BaseRepositoryTest () {
        flywayService = new FlywayService();
        connectionPool = JdbcConnectionPool.create(Properties.H2_URL, Properties.H2_USER, Properties.H2_PASSWORD);
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
