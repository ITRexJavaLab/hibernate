package com.itrex.java.lab.basemapping.example1;

import com.itrex.java.lab.basemapping.example1.entity.Employee;
import com.itrex.java.lab.basemapping.service.FlywayService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDemo {

    public static void main(String[] args) {
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();
        /**
         * new Configuration() - configure hibernate from our config file
         * buildSessionFactory() - create SessionFactory, it should be closed after
         */
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Employee employee = new Employee(1L,"Ivan");
            session.save(employee);
            session.getTransaction().commit();
            session.close();
        }
    }
}
