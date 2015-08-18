package com.demo.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		//entityManager.persist(entity);
		entityManager.merge(entity);
		//entityManager.flush();
		return entity;
	}

	@Override
	public Customer get(Customer entity) {
		Customer cust=entityManager.find(Customer.class, entity);
		return cust;
	}

	@Override
	public Object remove(Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<Customer> query(Object... param) {
		List<Customer> results=new ArrayList<Customer>();
		System.out.println(param[0]);
		if (param[0].equals("BY_EMAIL")) {
			String custId=(String)param[1];
			System.out.println(custId);
			System.out.println(this.getEntityManager());
			
			Query q=this.getEntityManager().createQuery("SELECT cust from Customer cust WHERE cust.customerId=:custId");
			System.out.println("query "+q);
			q.setParameter("custId", custId);
			results=q.getResultList();
			
		}
		return results;
	}
}
