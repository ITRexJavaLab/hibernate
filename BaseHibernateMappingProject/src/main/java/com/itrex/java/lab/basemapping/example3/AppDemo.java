package com.itrex.java.lab.basemapping.example3;

import com.itrex.java.lab.basemapping.example3.entity.Address;
import com.itrex.java.lab.basemapping.example3.entity.Employee3;
import com.itrex.java.lab.basemapping.example3.entity.Gender;
import com.itrex.java.lab.basemapping.service.FlywayService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppDemo {

    public static void main(String[] args) {
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            saveEmployeeWithoutAddress(session);
            saveEmployeeWithEmbeddedEntity(session);
//            findEmployee(session);
            session.getTransaction().commit();
        }
    }
    private static void findEmployee(Session session) {
        Employee3 employee = session.find(Employee3.class, 2L);
    }

    private static void saveEmployeeWithoutAddress(Session session) {
        session.save(new Employee3(null, Gender.MALE));
    }

    private static void saveEmployeeWithEmbeddedEntity(Session session) {
        session.save(new Employee3("Ivan", Gender.MALE, new Address("Minsk", "Street1"), new Address("Grodno", "Street2")));
        session.get(Employee3.class, 1L);
    }

}
