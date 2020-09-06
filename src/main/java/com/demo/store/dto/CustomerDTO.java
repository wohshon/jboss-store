package com.demo.store.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerDTO implements Serializable {
    

    /**
	 *
	 */
	private static final long serialVersionUID = -7461946336939692603L;
	private String name;
    private String email;
    private String deliveryAddress;
    private String contactInfo;
}
