package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.store.entity.Customer;
@Repository
@Transactional
public class CustomerService implements DataService<Customer> {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Customer> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer add(Customer entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public Customer get(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
