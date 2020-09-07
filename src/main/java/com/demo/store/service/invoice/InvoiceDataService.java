package com.demo.store.service.invoice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.store.entity.Invoice;
import com.demo.store.service.DataService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceDataService implements DataService<Invoice> {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public Invoice get(Invoice entity) {
        return em.find(Invoice.class, entity.getId());
        
    }

    @Override
    public List<Invoice> getAll() {
        return em.createQuery("FROM Invoice inv").getResultList();
    }

    @Override
    public Invoice getByEntityId(Invoice entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Invoice> query(String query, Object... param) {
        return em.createNamedQuery(query, Invoice.class).setParameter((String)param[0], param[1]).getResultList();
        
    }

    @Override
    public Invoice remove(Invoice entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public Invoice save(Invoice entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Invoice update(Invoice entity) {
        return em.merge(entity);
    }
}
