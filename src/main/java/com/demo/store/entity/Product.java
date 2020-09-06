package com.demo.store.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="products")
@Data
@NamedQueries({
    @NamedQuery(name="Product.findAll",
                query="SELECT p FROM Product p"),
    @NamedQuery(name="Product.findByName",
                query="SELECT p FROM Product p WHERE p.name LIKE :name"),
}) 
public class Product extends AbstractGeneratedIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602231572577587307L;
	@Column(name="productId", unique = true)
	private String productId;
	@Column(name = "shortDesc")
	private String shortDescription;
	@Column(name = "descp")
	private String description;
	@Column(name = "price")
	private Double price;
	@Column(name="img")
	private String img;
	@Override
	public Long getId() {
		return this.id;
	} 

}
