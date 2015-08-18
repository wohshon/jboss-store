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

@Entity
@Table(name="orders")
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
	@Column(name="customerId")
	private String customerId;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<OrderItem> items=new ArrayList<OrderItem>();
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
	
	
}
