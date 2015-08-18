package com.demo.store.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.demo.store.entity.AbstractGeneratedIdEntity;


public interface DataService<E extends AbstractGeneratedIdEntity> {

	public List<E> getAllEntities();
	public E add(E entity);
	public E get(E entity);
	public Object remove(E entity);
	public EntityManager getEntityManager();
	public List<E> query(Object... param);
	
	
}
