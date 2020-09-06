package com.demo.store.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartItemDTO implements Serializable  {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 8245691480733395809L;
    private ProductDTO product;
    private Integer qty;

}
