package com.demo.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
@Data
public class CartItem extends AbstractGeneratedIdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659623566387919721L;

	@Override
	public Long getId() {
		return id;
	}
	private String productId = "";//conv variable for web
	private Product item;
	private Double linePrice;
	private Integer qty;

	public Double getLinePrice() {
		return this.getQty()*this.getItem().getPrice().doubleValue();
	}

	public String getProductId() {
		return this.item.getProductId();
	}
	

	
	
	
	

}
