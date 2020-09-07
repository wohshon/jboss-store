package com.demo.store.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.store.entity.status.DeliveryStatus;

import lombok.Data;

@Entity
@Table(name="delivery")
@Data
@NamedQueries({
    @NamedQuery(name="Delivery.findAll",
                query="SELECT del FROM Delivery del"),
	@NamedQuery(name="Delivery.findByOrder",
                query="SELECT del FROM Delivery del WHERE del.order IN (:deliveryOrders)")				
}) 
public class Delivery extends AbstractGeneratedIdEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
 
    @JoinColumn(name = "del_order", referencedColumnName = "id")
    @OneToOne
    private Order order;

	@Column(name="deliveryDate")
	@Temporal(TemporalType.DATE)
    private Date deliveryDate;
    
    @Column(name="status")
    private DeliveryStatus status = DeliveryStatus.NEW;
	@Override
	public Long getId() {
		return id;
	}
}
