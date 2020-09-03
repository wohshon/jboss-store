package com.demo.store.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order extends AbstractGeneratedIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1303031475721699087L;

	@Column(name="orderDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Column(name="totalPrice")
	private BigDecimal totalPrice;
	//TODO need to come back to fix this contraint thingy, using a hack of customerid now
	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="customer", referencedColumnName="email",updatable=false)
	@Transient 
	private Customer customer;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<OrderItem> items=new ArrayList<OrderItem>();
	@Override
	public Long getId() {
		return id;
	}

	
	
	
	
	
}
