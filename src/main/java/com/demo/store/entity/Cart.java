package com.demo.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8711429908091000910L;
	private List<CartItem> items=new ArrayList<CartItem>();
	private BigDecimal totalPrice=BigDecimal.ZERO;
	
	public void refresh() {
		this.totalPrice=BigDecimal.ZERO;
		for (CartItem item:items) {
			System.out.println("-----------------"+item);
			System.out.println("-----------------"+item.getLinePrice());
			item.setLinePrice(item.getItemPrice()*item.getQty());
			this.totalPrice=this.totalPrice.add(BigDecimal.valueOf(item.getLinePrice()));
		}
		
	}

	public void removeItem(CartItem deleteItem) {
		Iterator<CartItem> iterator=this.getItems().iterator();
		while (iterator.hasNext()) {
			CartItem item=iterator.next();
			if (item.getItemId().equals(deleteItem.getItemId())) {
				System.out.println("removed item "+item.getItemId());
				iterator.remove();
			}
		}
		System.out.println("Final size :"+this.getItems().size());
		
		
	}
	public void addItem(CartItem cartItem) {
		boolean existing=false;
		for (CartItem item:items) {
			System.out.println(item.getItemId()+" "+cartItem.getItemId());
			if (item.getItemId().equals(cartItem.getItemId())) {
				//item.setQty(item.getQty()+cartItem.getQty());
				item.setQty(cartItem.getQty());
				existing=true;
			}
		}
		if (!existing) {
			this.getItems().add(cartItem);
		}
		
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartItem> getItems() {
		//TODO
		//should return a clone to prevent direct edit
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
}
