package com.demo.store.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDTO implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -8591106468398396110L;
    private String productId;
    private String name;

}
