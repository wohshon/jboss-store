package com.demo.store.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@OneToOne
	@JoinColumn(name="customer", referencedColumnName="email",updatable=false)
	private Customer customer;
	//@Transient
	//private List<OrderItem> items;
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
	/*public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	*/
	
	
	
	
}
