package com.itrex.java.lab.cascade;


import com.itrex.java.lab.cascade.entity.mtm.Newspaper;
import com.itrex.java.lab.cascade.entity.mtm.Publisher;
import com.itrex.java.lab.cascade.entity.mto.Book;
import com.itrex.java.lab.cascade.entity.mto.BookCategory;
import com.itrex.java.lab.cascade.entity.oto.User;
import com.itrex.java.lab.cascade.entity.oto.Address;
import com.itrex.java.lab.cascade.service.FlywayService;
import com.itrex.java.lab.cascade.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;


@Log4j2
public class CascadeRunner {

    public static void main(String[] args) {
        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        cascadeFromOwnerOTO();
//             cascadeFromNotOwnerOTO();
//                cascadeFromOwnerMTO();
//                cascadeFromNotOwnerMTO();
//        cascadeClearFromOwnerMTO();

//TODO comment flywayService.migrate();
//TODO uncomment <property name="hibernate.hbm2ddl.auto">create</property>
//        autoCreateTablesOTO();
//        autoCreateTablesMTO();
//        autoCreateTablesMTM();
    }

    public static void cascadeFromOwnerOTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, 1);

            try {
                Transaction transaction = session.beginTransaction();
                session.delete(user);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logEntitiesOTOAfter(session, 1);
        }
    }

    public static void cascadeFromNotOwnerOTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Address address = session.get(Address.class, 1);

            try {
                Transaction transaction = session.beginTransaction();
                session.delete(address);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logEntitiesOTOAfter(session, 1);
        }
    }

    public static void cascadeFromOwnerMTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Book book = session.get(Book.class, 1);
            BookCategory category = session.get(BookCategory.class, 1);

            log.info("size of category.books: {}", category.getBooks() == null ? 0 : category.getBooks().size());

            try {
                Transaction transaction = session.beginTransaction();
                session.delete(book);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logEntitiesMTOAfter(session, 1);
        }
    }

    public static void cascadeFromNotOwnerMTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            BookCategory category = session.get(BookCategory.class, 1);

            try {
                Transaction transaction = session.beginTransaction();
                session.delete(category);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logEntitiesMTOAfter(session, 1);
        }
    }

    public static void cascadeClearFromOwnerMTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            BookCategory category = session.get(BookCategory.class, 1);

            log.info("size of category.books: {}", category.getBooks() == null ? 0 : category.getBooks().size());

            try {
                Transaction transaction = session.beginTransaction();
//                category.getBooks().remove(0);
                category.getBooks().clear();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logEntitiesMTOAfter(session, 1);
        }
    }

    public static void autoCreateTablesOTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Address address1 = new Address();
            address1.setName("Berlin");

            Address address2 = new Address();
            address2.setName("Madrid");

            User user1 = new User();
            user1.setName("Ruslan");
           user1.setAddress(address1);

            User user2 = new User();
            user2.setName("Max");
            user2.setAddress(address2);

            Transaction transaction = session.beginTransaction();
            session.save(user1);
            session.save(user2);
            transaction.commit();

            logEntitiesOTOAfter(session, 1);
        }
    }

    public static void autoCreateTablesMTO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            BookCategory category1 = new BookCategory();
            category1.setName("Novel");

            BookCategory category2 = new BookCategory();
            category2.setName("Poetry");

            Book book1 = new Book();
            book1.setName("book 1");
           book1.setBookCategory(category1);

            Book book2 = new Book();
            book2.setName("book 2");
            book2.setBookCategory(category2);

            Book book3 = new Book();
            book3.setName("book 3");
            book3.setBookCategory(category1);

            Transaction transaction = session.beginTransaction();
            session.save(book1);
            session.save(book2);
            session.save(book3);
            transaction.commit();

            logEntitiesMTOAfter(session, 1);
        }
    }

    public static void autoCreateTablesMTM() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Publisher publisher1 = new Publisher();
            publisher1.setName("publisher1");
            publisher1.setNewspapers(new ArrayList<>());

            Publisher publisher2 = new Publisher();
            publisher2.setName("publisher2");
            publisher2.setNewspapers(new ArrayList<>());

            Newspaper newspaper1 = new Newspaper();
            newspaper1.setName("newspaper1");
            newspaper1.setPublishers(new ArrayList<>());

            Newspaper newspaper2 = new Newspaper();
            newspaper2.setName("newspaper2");
            newspaper2.setPublishers(new ArrayList<>());

            Newspaper newspaper3 = new Newspaper();
            newspaper3.setName("newspaper3");
            newspaper3.setPublishers(new ArrayList<>());

            publisher1.getNewspapers().add(newspaper1);
            publisher1.getNewspapers().add(newspaper2);
            publisher2.getNewspapers().add(newspaper1);
            publisher2.getNewspapers().add(newspaper3);
            newspaper1.getPublishers().add(publisher1);
            newspaper1.getPublishers().add(publisher2);
            newspaper2.getPublishers().add(publisher1);
            newspaper3.getPublishers().add(publisher2);

            Transaction transaction = session.beginTransaction();
            session.save(publisher1);
            session.save(publisher2);
            transaction.commit();

            logEntitiesMTMAfter(session, 1);
        }
    }

    public static void logEntitiesOTOAfter(Session session, Integer id) {
        User userAfter = session.get(User.class, id);
        Address addressAfter = session.get(Address.class, id);
        log.info("user: {}", userAfter != null ? "exists" : "null");
        log.info("address: {}", addressAfter != null ? "exists" : "null");
    }

    public static void logEntitiesMTOAfter(Session session, Integer id) {
        Book bookAfter = session.get(Book.class, id);
        BookCategory categoryAfter = session.get(BookCategory.class, id);
        Book bookAfter2 = session.get(Book.class, 3);
        log.info("book: {}", bookAfter != null ? "exists" : "null");
        log.info("category: {}", categoryAfter != null ? "exists" : "null");
        log.info("book2: {}", bookAfter2 != null ? "exists" : "null");

        if (categoryAfter != null) {
            log.info("size of category.books: {}", categoryAfter.getBooks() == null ? 0 : categoryAfter.getBooks().size());
        }
    }

    public static void logEntitiesMTMAfter(Session session, Integer id) {
        Newspaper newspaperAfter = session.get(Newspaper.class, id);
        Publisher publisherAfter = session.get(Publisher.class, id);
        log.info("newspaper: {}", newspaperAfter != null ? "exists" : "null");
        log.info("publisher {}", publisherAfter != null ? "exists" : "null");

        if (newspaperAfter != null) {
            log.info("size of newspaper.publishers: {}", newspaperAfter.getPublishers() == null ? 0 : newspaperAfter.getPublishers().size());
        }

        if (publisherAfter != null) {
            log.info("size of publisher.newspapers: {}", publisherAfter.getNewspapers() == null ? 0 : publisherAfter.getNewspapers().size());
        }
    }
}