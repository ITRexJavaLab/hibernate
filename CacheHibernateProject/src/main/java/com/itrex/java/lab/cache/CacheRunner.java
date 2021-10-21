package com.itrex.java.lab.cache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itrex.java.lab.cache.entity.Account;
import com.itrex.java.lab.cache.entity.Client;
import com.itrex.java.lab.cache.entity.batch.Account3;
import com.itrex.java.lab.cache.entity.batch.Client3;
import com.itrex.java.lab.cache.entity.subselect.Client2;
import com.itrex.java.lab.cache.service.FlywayService;
import com.itrex.java.lab.cache.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public class CacheRunner {

    public static void main(String[] args) {
        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        sqlCountAssertion();

//        npo();
//        joinFetch();
//        subSelect();
//        batchFetching();
//        batchSizeCache();

//        criteriaAPI();
    }

    public static void sqlCountAssertion() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account1 = session.get(Account.class, 1);
            Account account2 = session.get(Account.class, 2);
            Account account3 = session.get(Account.class, 1);
            //select 2
        }
    }

    public static void npo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> clients =
                    session.createQuery("from Client c where c.age >= :age")
                            .setParameter("age", 18)
                            .list();
            clients.forEach(c -> c.getAccounts().size());

            //select 8
        }
    }

    public static void joinFetch() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> clients =
                    session.createQuery("from Client c join fetch c.accounts where c.age >= :age")
                            .setParameter("age", 30)
                            .list();
            clients.forEach(c -> c.getAccounts().size());

            //select 1
        }
    }

    public static void subSelect() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client2> clients =
                    session.createQuery("from Client2 c where c.age >= :age")
                            .setParameter("age", 30)
                            .list();
            clients.forEach(c -> c.getAccounts().size());

            //select 2
        }
    }

    public static void batchFetching() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client3> clients =
                    session.createQuery("from Client3 c where c.age >= :age")
                            .setParameter("age", 18)
                            .list();
            clients.forEach(c -> c.getAccounts().size());

            log.info("!!!! count {}", clients.size());

            //select 3
        }
    }

    public static void batchSizeCache() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account3 account1 = session.get(Account3.class, 1);
            Account3 account2 = session.get(Account3.class, 2);
            Account3 account3 = session.get(Account3.class, 3);
            account1.getClient().getName();

            //select 3
        }
    }

    public static void criteriaAPI() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteria = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = criteria.from(Client.class);
            root.fetch("accounts");
            criteria.select(root).where(criteriaBuilder.greaterThan(root.get("age"), 30));

            Query<Client> criteriaQuery = session.createQuery(criteria);
            log.info("!!!" + new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(criteriaQuery.getResultList()));
        }
    }
}