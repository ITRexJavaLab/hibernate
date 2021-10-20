package com.itrex.java.lab.basemapping.example1.dao;

import com.itrex.java.lab.basemapping.example1.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EmployeeDaoTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void after() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Employee3").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        Employee ivan = new Employee("Ivan");
        try (Session session = FACTORY.openSession()) {
            Object id = session.save(ivan);
            assertNotNull("Object is null", id);
        }
    }

    @Test
    public void checkFindEntity() {
        Employee petr = new Employee("Petr");
        try (Session session = FACTORY.openSession()) {
            Object id = session.save(petr);
            assertNotNull("Object is null", id);
            Employee employee = session.find(Employee.class, id);
            assertNotNull("Employee is null", employee);
        }
    }

}
