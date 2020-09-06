package com.demo.store.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="orderItems")
public class OrderItem extends AbstractGeneratedIdEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6155461947300181455L;
	//@OneToOne(cascade=CascadeType.MERGE)
	//private Product product;
	@Column(name="qty")
	private Integer qty;
	@Column(name="lineCost")
	private Double lineCost;
	@OneToOne(cascade=CascadeType.ALL)
	private Order order;

	@OneToOne
	@JoinColumn(referencedColumnName = "productId")
	private Product item;
	
	@Override
	public Long getId() {
		return id;
	}



	
	

}
