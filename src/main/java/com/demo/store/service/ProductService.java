package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.demo.store.entity.Product;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@Repository
public class ProductService {

    @PersistenceContext
    EntityManager em;
    


    public void test() {
        //query
        
       List list =  em.createQuery("from Product where name = :n").setParameter("n", "Container").getResultList();
       log.info(" Got results "+list.size());
       Product p = (Product)list.get(0);
       log.info("product "+p.getName());
        p.setDescription("random desc");
        em.merge(p);
        p = new Product();
        p.setName("new");
        em.persist(p);



    }
 
}
/** 
 * //keeping ths hibernation localsessionfactory reference codes
 * 
    @Autowired
    private SessionFactory sessionFactory;

    public void test() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
             int count = session.createSQLQuery("select * from products").list().size();
             log.info("Products - {} ", count);
            Product p = (Product)session.createQuery("from Product where name = :n").setParameter("n","Container" ).uniqueResult();
            log.info("Products query - {} ", p.getId());
            //write
            log.info("Products - new");

            p = new Product();
            p.setName("test");
            session.persist(p);
            //p.setDescription("desc");
            //p.setName("test");
            //session.saveOrUpdate(p);
            //p.setId(1L);
            //session.merge(p);
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) session.close();
            log.info("hbm session closed");
        }
    }
  
  
 */