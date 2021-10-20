package com.itrex.java.lab.basemapping.example2;

import java.io.Serializable;

import com.google.gson.Gson;
import com.itrex.java.lab.basemapping.example2.entity.Employee2;
import com.itrex.java.lab.basemapping.example2.entity.EmployeeSequence;
import com.itrex.java.lab.basemapping.example2.entity.EmployeeTable;
import com.itrex.java.lab.basemapping.example2.entity.Gender;
import com.itrex.java.lab.basemapping.service.FlywayService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log4j2
public class ApplicationDemo {

    /**
     * fatal -> error -> warn -> info -> debug -> trace
     * */
    public static void main(String[] args) {
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        log.info("Checking table sequence");
//        getObjectFromSessionCache();
//        getObjectFromDataBaseSelect();
//        getObjectAfterDelete();
//        checkEnumMapping();
        saveBySequenceGenerationStrategy();
//        checkTableSequenceGenerationStrategy();
    }

    private static void checkTableSequenceGenerationStrategy() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new EmployeeTable("Ivan"));
            session.save(new EmployeeTable("Sveta"));
            session.save(new EmployeeTable("Klara"));
            session.getTransaction().commit();
        }
    }

    private static void saveBySequenceGenerationStrategy() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            /**
             * first sequence for all object for getting id
             * than insert all object to DB
             * */
            session.save(new EmployeeSequence("Ivan"));
            session.save(new EmployeeSequence("Petr"));
            session.save(new EmployeeSequence("Carl"));
            session.getTransaction().commit();
        }
    }

    private static void checkEnumMapping() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee2 ivan = new Employee2("Ivan", Gender.MALE);
            session.save(ivan);
            session.getTransaction().commit();
        }
    }

//    third example get deleted object
    private static void getObjectAfterDelete() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee2 employee = session.get(Employee2.class, 2L);
            session.delete(employee);
            /**
             * dirty session, when object changed in session, but not commited to DB
             * method evict - deletes object from session, not from DB
             * method clear - cleans session
             * method merge - adds changes from current object to gotten object from DB
             * */
            Employee2 deletedEmployee = session.get(Employee2.class, 2L);
            /**
             * object removed from session (Removed state) but not removed yet in DB (dirty session)
             */
            log.info(new Gson().toJson(deletedEmployee));
            session.getTransaction().commit();
        }
    }

//    second example detached object
    private static void getObjectFromDataBaseSelect() {
        Employee2 employee;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employee2 sveta = new Employee2("Sveta2");
            Serializable id = session.save(sveta);
            employee = session.get(Employee2.class, id);
            session.getTransaction().commit();
            log.info(new Gson().toJson(sveta));
        }
        /**
         * detached status of employee entity
         */
        log.info(new Gson().toJson(employee));
    }

//    first example get from session
    private static void getObjectFromSessionCache() {
        try (SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Employee2 sveta = new Employee2("Sveta");
            /**
             * method persist - like save, but not work without transaction
             * */
            Serializable id = session.save(sveta);
            /**
             * methods get, find returns object from DB
             * method load returns proxy of this Object from DB - uses for collection lazy loading
             * */
            Employee2 employee = session.get(Employee2.class, id);
//            no select query by hibernate
            log.info(new Gson().toJson(sveta));
        }
    }

}
