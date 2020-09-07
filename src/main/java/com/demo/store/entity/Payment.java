package com.demo.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.store.entity.status.PaymentStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
@Table(name = "payment")
public class Payment extends AbstractGeneratedIdEntity {
    /**
    *
    */
    private static final long serialVersionUID = -3555683044720477238L;
    @OneToOne
    @JoinColumn(name = "payment_invoice", referencedColumnName = "id")
    private Invoice invoice;
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name="paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    
	@Override
	public Long getId() {
		return id;
	}
    
}
