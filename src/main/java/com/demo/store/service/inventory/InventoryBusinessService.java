package com.demo.store.service.inventory;

import javax.transaction.Transactional;

import com.demo.store.entity.Inventory;
import com.demo.store.entity.OrderItem;
import org.springframework.stereotype.Component;


@Component
@Transactional
public interface InventoryBusinessService {

    public void updateInventory(OrderItem orderItem) throws Exception;

    public Inventory updateInventoryItem(Inventory inv) throws Exception;
}
