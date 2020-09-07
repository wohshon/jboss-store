package com.demo.store.service.delivery;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.store.entity.Delivery;
import com.demo.store.entity.Order;
import com.demo.store.service.DataService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeliveryDataService implements DataService<Delivery> {
    @PersistenceContext
    EntityManager em;
    @Override
    public Delivery get(Delivery entity) {
        return em.find(Delivery.class, entity.getId());
        
    }

    @Override
    public List<Delivery> getAll() {
        return em.createQuery("FROM Delivery").getResultList();
    }

    @Override
    public Delivery getByEntityId(Delivery entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    
    public List<Delivery> query(String query, Object... param) {
        return em.createNamedQuery(query, Delivery.class).setParameter((String)param[0], param[1]).getResultList();
        //return em.createNamedQuery(query, Delivery.class).setParameter("deliveryOrders", Arrays.asList(param)).getResultList();

    }

    @Override
    public Delivery save(Delivery entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Delivery remove(Delivery entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public Delivery update(Delivery entity) {
        return em.merge(entity);
    }
}
