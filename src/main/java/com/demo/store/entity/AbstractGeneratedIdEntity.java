package com.demo.store.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractGeneratedIdEntity extends AbstractEntity<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6410112632045029555L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // uses mysql auto generation
	@Column(name = "id")
	Long id;
	
}
