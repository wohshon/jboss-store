package com.demo.store.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="orderItems")
public class OrderItem extends AbstractGeneratedIdEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6155461947300181455L;
	@OneToOne(cascade=CascadeType.MERGE)
	private Product product;
	@Column(name="qty")
	private Integer qty;
	@Column(name="lineCost")
	private BigDecimal lineCost;
	@OneToOne(cascade=CascadeType.ALL)
	private Order order;
	@Override
	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getLineCost() {
		return lineCost;
	}

	public void setLineCost(BigDecimal lineCost) {
		this.lineCost = lineCost;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
