package com.demo.store.service;

import java.util.List;

import javax.transaction.Transactional;

import com.demo.store.entity.AbstractGeneratedIdEntity;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Component
public interface DataService<E extends AbstractGeneratedIdEntity> {


    public List<E> getAll();
    public E get(E entity);
    public E add(E entity);
    public E remove(E entity);
    public E update(E entity);
    public List<E> query(String query, Object...param);
    
}
