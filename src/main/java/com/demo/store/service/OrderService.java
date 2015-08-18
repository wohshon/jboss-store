package com.demo.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		//entityManager.refresh(entity.getCustomer());
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
		Order order=this.getEntityManager().find(Order.class, entity.getId());
		return order;
	}

	@Override
	public List<Order> query(Object... param) {
		List<Order> results=new ArrayList<Order>();
		if (param[0].equals("BYCUSTOMER")) {
			String custId=(String)param[1];
//			Query q=this.getEntityManager().createQuery("SELECT order from Order order WHERE order.customer.customerId=:custId");
			Query q=this.getEntityManager().createQuery("SELECT order from Order order WHERE order.customerId=:custId");
			q.setParameter("custId", custId);
			results=q.getResultList();
		}
		return results;
	}
}
