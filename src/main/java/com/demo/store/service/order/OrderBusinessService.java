package com.demo.store.service.order;

import javax.transaction.Transactional;

import com.demo.store.entity.Cart;
import com.demo.store.entity.Order;

import org.springframework.stereotype.Component;

@Component
@Transactional
public interface OrderBusinessService {
    
    public Order generateOrder(Order order, Cart cart) throws Exception;
}
