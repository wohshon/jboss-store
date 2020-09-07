package com.demo.store.service.invoice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.demo.store.entity.Invoice;
import com.demo.store.entity.Order;
import com.demo.store.entity.status.InvoiceStatus;
import com.demo.store.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceBusinessServiceImpl implements InvoiceBusinessService {
    
    @Autowired
    DataService<Invoice> invoiceDataService;
    @Override
    public Invoice generateInvoice(Order order) throws Exception {
        Invoice inv = new Invoice();
        inv.setInvoiceDate(new Date());
        inv.setOrder(order);
        inv.setName(order.getName());
        inv.setStatus(InvoiceStatus.ISSUED);
        invoiceDataService.save(inv);
        return inv ;
    }
    @Override
    public List<Invoice> getInvoicesByOrder(List<Order> orders) throws Exception {
        Order[] orderParams = new Order[orders.size()];
        int i = 0;
        for (Order order : orders) {
            log.info("Orders {}",order.getName());
            orderParams[i++]= order;
        }
        Object[] searchParams = {"invoiceOrders",Arrays.asList(orderParams)};
        List<Invoice> invoices = invoiceDataService.query("Invoice.findByOrder", searchParams);
        return invoices;
    }
}
