package com.demo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="customers")
@Data
public class Customer extends AbstractGeneratedIdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2783945080260243551L;
	@Column(name="email",unique=true)
	private String email;
	@Column(name="address")
	private String address;
	@Column(name="contact")
	private String contact;

	
	@Override
	public Long getId() {
		return id;
	}

	
	
}
