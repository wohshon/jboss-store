package com.demo.store.entity;

import java.math.BigDecimal;

public class OrderItem extends AbstractGeneratedIdEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6155461947300181455L;

	private Product product;
	private Integer qty;
	private BigDecimal lineCost;
	private Order order;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
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
