package com.demo.store.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends com.demo.store.entity.AbstractGeneratedIdEntity_ {

	public static volatile SingularAttribute<Product, BigDecimal> price;
	public static volatile SingularAttribute<Product, String> shortDescription;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, String> img;

}

