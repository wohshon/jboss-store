package com.demo.store.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="products")
@Data
public class Product extends AbstractGeneratedIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602231572577587307L;
	@Column(name = "shortDesc")
	private String shortDescription;
	@Column(name = "descp")
	private String description;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name="img")
	private String img;
	@Override
	public Long getId() {
		return this.id;
	}

}
