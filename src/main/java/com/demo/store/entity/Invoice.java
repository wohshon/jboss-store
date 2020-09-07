package com.demo.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.store.entity.status.InvoiceStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
@Table(name ="invoices")
@NamedQueries({
    @NamedQuery(name="Invoice.findAll",
                query="SELECT inv FROM Invoice inv"),
	@NamedQuery(name="Invoice.findByOrder",
                query="SELECT inv FROM Invoice inv WHERE inv.order IN (:invoiceOrders)")				
}) 
public class Invoice extends AbstractGeneratedIdEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    @JoinColumn(name = "invoice_order", referencedColumnName = "id")
    private Order order;
    @Column(name = "invoiceDate")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @Column(name = "status")
    private InvoiceStatus status = InvoiceStatus.NEW;
    @Override
	public Long getId() {
		return id;
	}
}
