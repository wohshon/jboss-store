package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.store.entity.Inventory;
import com.demo.store.entity.OrderItem;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryService implements DataService<Inventory> {
    
    @PersistenceContext
    EntityManager em;
    @Override
    public Inventory get(Inventory entity) {
        // TODO Auto-generated method stub
        return em.find(Inventory.class, entity.getId());
    }

    @Override
    public List<Inventory> getAll() {
        return em.createQuery("FROM Inventory").getResultList();
        
    }

    @Override
    public Inventory getByEntityId(Inventory entity) {
        return (Inventory)em.createQuery("FROM Inventory WHERE name = :name").setParameter("name", entity.getName()).getSingleResult();
    }

    @Override
    public List<Inventory> query(String query, Object... param) {
        return em.createNamedQuery(query, Inventory.class).setParameter((String)param[0], param[1]).getResultList();

    }

    @Override
    public Inventory save(Inventory entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Inventory remove(Inventory entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public Inventory update(Inventory entity) {
       em.merge(entity);
        return entity;
    }


}

