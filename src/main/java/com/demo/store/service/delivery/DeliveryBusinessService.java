package com.demo.store.service.delivery;

import javax.transaction.Transactional;

import com.demo.store.entity.Delivery;
import com.demo.store.entity.Order;

import org.springframework.stereotype.Component;

@Transactional
@Component
public interface DeliveryBusinessService {
    
    public Delivery raiseDeliveryRequest(Delivery delivery, Order order) throws Exception;

    public Delivery updateDelivery(Delivery delivery) throws Exception;
}
