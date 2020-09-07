package com.demo.store.service;

import java.util.Calendar;

import com.demo.store.entity.Delivery;
import com.demo.store.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryBusinessServiceImpl implements DeliveryBusinessService {
    @Autowired
    DataService<Delivery> deliveryService;
    @Override
    public Delivery raiseDeliveryRequest(Order order) throws Exception {
        Delivery del = new Delivery();
        del.setOrder(order);
        del.setName(order.getName());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        del.setDeliveryDate(cal.getTime());
        deliveryService.save(del);        
        
        /**Simulate calling a external vendor to arrange for delivery 
         * can be a queue, or rest api and can set the status of the delivery accordingly
        */
        return del;
    }
    @Override
    public Delivery updatDelivery() {
        // TODO Auto-generated method stub
        return null;
    }
}
