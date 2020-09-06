package com.demo.store.service;

import javax.transaction.Transactional;

import com.demo.store.entity.Inventory;
import com.demo.store.entity.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Transactional
@Slf4j
public class InventoryBusinessService {
    @Autowired
    DataService<Inventory> inventoryService;

    public void updateInventory(OrderItem orderItem) throws Exception {

        log.info("Inside Inventory ,order {}", orderItem.getItem().getName());
        
        Object[] params = {"product", orderItem.getItem()};        
        Inventory inv = inventoryService.query("Inventory.findByProduct", params).get(0); // dirty hack
        log.info("inv {} {}", inv.getName(), inv.getStock());
        Integer i = inv.getStock() - orderItem.getQty();
        if (i < 0) {
            throw new Exception("Not enough Stock");
        }
        inv.setStock(inv.getStock() - orderItem.getQty());
        inventoryService.update(inv);

    }
}
