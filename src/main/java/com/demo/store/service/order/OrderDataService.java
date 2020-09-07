package com.demo.store.service.order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.store.entity.Order;
import com.demo.store.service.DataService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderDataService implements DataService<Order> {

    @PersistenceContext
    EntityManager em;

    @Override
    public Order save(Order entity) {
        em.persist(entity);
        return entity;
    }
    @Override
    public Order get(Order entity) {
        return em.find(Order.class, entity.getId());
        
    }
    @Override
    public List<Order> getAll() {
        
        return em.createQuery("FROM Order").getResultList();
    }
    @Override
    public Order getByEntityId(Order entity) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    //ugly , supports single parameter with namedquery now
    public List<Order> query(String query, Object... param) {
        return em.createNamedQuery(query, Order.class).setParameter((String)param[0], param[1]).getResultList();
        
    }
    @Override
    public Order remove(Order entity) {
        em.remove(entity);
        return entity;
    }
    @Override
    public Order update(Order entity) {
        return em.merge(entity);

    }
    
}
