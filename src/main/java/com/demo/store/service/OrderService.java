package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.store.entity.Order;
@Repository
@Transactional
public class OrderService implements DataService<Order> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return this.entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
	@Override
	public List<Order> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order add(Order entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public Order get(Order entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Order entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
