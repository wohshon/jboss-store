package com.demo.store.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="products")
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
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	

}
