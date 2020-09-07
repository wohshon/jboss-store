package com.demo.store.service;

import javax.transaction.Transactional;

import com.demo.store.entity.Delivery;
import com.demo.store.entity.Order;

import org.springframework.stereotype.Component;

@Transactional
@Component
public interface DeliveryBusinessService {
    
    public Delivery raiseDeliveryRequest(Order order) throws Exception;

    public Delivery updatDelivery(Delivery) throws Exception;
}
