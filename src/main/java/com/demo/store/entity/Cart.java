package com.demo.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8711429908091000910L;
	private List<CartItem> items=new ArrayList<CartItem>();
	private BigDecimal totalPrice=BigDecimal.ZERO;
	private Customer customer;
	
	public void refresh() {
		this.totalPrice=BigDecimal.ZERO;
		for (CartItem item:items) {
			log.info("item: {} ",item);
			item.setLinePrice(item.getItem().getPrice().doubleValue()*item.getQty());
			log.info("line price: {}",item.getLinePrice());
			this.totalPrice=this.totalPrice.add(BigDecimal.valueOf(item.getLinePrice()));
		}
		
	}

	public void removeItem(CartItem deleteItem) {
		Iterator<CartItem> iterator=this.getItems().iterator();
		while (iterator.hasNext()) {
			CartItem item=iterator.next();
			if (item.getProductId().equals(deleteItem.getProductId())) {
				log.info("removed item: {} ",item.getProductId());
				iterator.remove();
			}
		}
		log.info("Final size : {}",this.getItems().size());
		
		
	}
	public void addItem(CartItem cartItem) {
		boolean existing=false;
		for (CartItem item:items) {
			log.info(item.getProductId()+" "+cartItem.getProductId());
			if (item.getProductId().equals(cartItem.getProductId())) {
				item.setQty(cartItem.getQty());
				existing=true;
			}
		}
		if (!existing) {
			this.getItems().add(cartItem);
		}
		
	}
	

	
}
