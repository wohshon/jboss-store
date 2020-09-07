package com.demo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="inventory")
@Data
@NamedQueries({
    @NamedQuery(name="Inventory.findAll",
                query="SELECT inv FROM Inventory inv"),
    @NamedQuery(name="Inventory.findByProduct",
                query="SELECT inv FROM Inventory inv WHERE inv.product = :product", lockMode = LockModeType.PESSIMISTIC_READ
                )			
}) 
public class Inventory extends AbstractGeneratedIdEntity{

    /**
     *
     */
    private static final long serialVersionUID = 8010273970975416405L;
    
    @JoinColumn(name = "product", referencedColumnName = "productId")
    @OneToOne
    private Product product;
    @Column(name="stock")
    private Integer stock;
    @Column(name="uom")
    private String uom;

    @Override
	public Long getId() {
		return id;
	}
}
