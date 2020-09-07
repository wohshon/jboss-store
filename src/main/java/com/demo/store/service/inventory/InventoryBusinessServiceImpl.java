package com.demo.store.service.inventory;

import com.demo.store.entity.Inventory;
import com.demo.store.entity.OrderItem;
import com.demo.store.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InventoryBusinessServiceImpl implements InventoryBusinessService{
    @Autowired
    DataService<Inventory> inventoryDataService;
    
    @Override
    public void updateInventory(OrderItem orderItem) throws Exception {
        
        log.info("Inside Inventory ,order {}", orderItem.getItem().getName());
        
        Object[] params = {"product", orderItem.getItem()};        
        Inventory inv = inventoryDataService.query("Inventory.findByProduct", params).get(0); // dirty hack
        log.info("inv {} {}", inv.getName(), inv.getStock());
        Integer i = inv.getStock() - orderItem.getQty();
        if (i < 0) {
            throw new Exception("Not enough Stock");
        }
        inv.setStock(inv.getStock() - orderItem.getQty());
        inventoryDataService.update(inv);

    }
    @Override
    public Inventory updateInventoryItem(Inventory inv) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
