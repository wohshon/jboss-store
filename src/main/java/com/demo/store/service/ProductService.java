package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.store.entity.Product;
@Repository
@Transactional
public class ProductService implements DataService<Product> {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Product add(Product entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;

		//return dao.add(entity);
		
	}
	
	@Override
	public Product get(Product entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object remove(Product entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllEntities() {
		Query q=entityManager.createQuery("SELECT prod from Product prod");
		List<Product> results=q.getResultList();
		return results;
//		/return dao.getAll();
	}
	
	@Override
	public List<Product> query(Object... param) {
		// TODO Auto-generated method stub
		return null;
	}
}
