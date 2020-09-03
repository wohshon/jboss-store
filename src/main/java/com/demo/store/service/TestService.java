package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.demo.store.entity.Product;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Component
@Slf4j
public class TestService {
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
