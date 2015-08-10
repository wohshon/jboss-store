package com.demo.store.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends com.demo.store.entity.AbstractGeneratedIdEntity_ {

	public static volatile SingularAttribute<Order, Date> orderDate;
	public static volatile SingularAttribute<Order, Customer> customer;
	public static volatile SingularAttribute<Order, BigDecimal> totalPrice;

}

