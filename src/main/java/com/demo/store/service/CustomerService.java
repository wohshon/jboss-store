package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.demo.store.entity.Customer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerService implements DataService<Customer> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public Customer getByEntityId(Customer entity) {
        Customer c = null;
        try {
         c = (Customer) em.createQuery("FROM Customer WHERE email = :email").setParameter("email", entity.getEmail()).getSingleResult();
        } catch (EmptyResultDataAccessException e) {
            log.info("Error in {}", this.getClass());
            e.printStackTrace();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    @Override
    public Customer get(Customer entity) {
        return em.find(Customer.class, entity.getId());
    }

    @Override
    public List<Customer> getAll() {
        return em.createQuery("FROM Customer").getResultList();

    }
    @Override
    public List<Customer> query(String query, Object... param) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Customer remove(Customer entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public Customer save(Customer entity) {
        em.persist(entity);
        return entity;
    }
    @Override
    public Customer update(Customer entity) {
        return em.merge(entity);
        
    }
    
}
