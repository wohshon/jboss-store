package com.demo.store.service.order;

import java.util.Date;

import com.demo.store.entity.Cart;
import com.demo.store.entity.CartItem;
import com.demo.store.entity.Order;
import com.demo.store.entity.OrderItem;
import com.demo.store.entity.status.OrderStatus;
import com.demo.store.service.DataService;
import com.demo.store.service.inventory.InventoryBusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderBusinessServiceImpl implements OrderBusinessService{

    @Autowired
    private DataService<Order> orderDataService;
    @Autowired
    private InventoryBusinessService inventoryBusinessService;

    @Override
    public Order generateOrder(Order order, Cart cart) throws Exception {
        OrderItem orderItem = null;
        order.setOrderDate(new Date());
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.CONFIRMED);
        // get customer from cart
        order.setCustomer(cart.getCustomer());
        for (CartItem cartItem : cart.getItems()) {
            orderItem = new OrderItem();
            orderItem.setItem(cartItem.getItem());
            orderItem.setQty(cartItem.getQty());
            orderItem.setLineCost(cartItem.getLinePrice());
            orderItem.setName(orderItem.getItem().getName());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
            //it will throw an Exception if stock is too low
            //TODO, use a business exception here
            inventoryBusinessService.updateInventory(orderItem);
        }
        orderDataService.save(order);
        return orderDataService.get(order);
    }
}
