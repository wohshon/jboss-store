package com.demo.store.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractIdEntity<T> extends AbstractEntity<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8654100449615734084L;
	@Id
	@Column(name = "id")
	T id;

}
