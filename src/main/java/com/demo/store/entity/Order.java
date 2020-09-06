package com.demo.store.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="orders")
@Data
@NamedQueries({
    @NamedQuery(name="Order.findAll",
                query="SELECT o FROM Order o"),
    @NamedQuery(name="Order.findByStatus",
				query="SELECT o FROM Order o WHERE o.status = :status"),
	@NamedQuery(name="Order.findByUsers",
                query="SELECT o FROM Order o WHERE o.customer = :customer")				
}) 
public class Order extends AbstractGeneratedIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1303031475721699087L;

	@Column(name="orderDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Column(name="totalPrice")
	private Double totalPrice;
	@Column(name = "status")
	private String status="NEW";
	@ManyToOne
	@JoinColumn(name="customer", referencedColumnName="email",updatable=false)
	//@Transient 
	private Customer customer;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<OrderItem> items=new ArrayList<OrderItem>();
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
	
	
}
