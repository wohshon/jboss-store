package com.demo.store.service;

import java.util.List;

import com.demo.store.entity.Product;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductService {
 
    @Autowired
    private SessionFactory sessionFactory;

    public void test() {
        try {
         int count = this.sessionFactory.openSession().createSQLQuery("select * from products").list().size();
         log.info("Products - {} ", count);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            this.sessionFactory.close();
            log.info("hbm session closed");
        }
    }
}
