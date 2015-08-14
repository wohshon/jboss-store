package com.demo.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem extends AbstractGeneratedIdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2659623566387919721L;

	@Override
	public Long getId() {
		return id;
	}
	private String itemId;//conv variable for web
	private Product item;
	private Double linePrice;
	private Double itemPrice;
	private String itemName;
	private Integer qty;
	public Product getItem() {
		return item;
	}
	public void setItem(Product item) {
		this.item = item;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Double getLinePrice() {
		return this.getQty()*this.getItemPrice();
	}
	public void setLinePrice(Double linePrice) {
		this.linePrice = linePrice;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

	
	
	
	

}
