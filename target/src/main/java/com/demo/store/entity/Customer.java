package com.demo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer extends AbstractGeneratedIdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2783945080260243551L;
	@Column(name="custId")
	private String customerId;
	@Column(name="email",unique=true)
	private String email;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
