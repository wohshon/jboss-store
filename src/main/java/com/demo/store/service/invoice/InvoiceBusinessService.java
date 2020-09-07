package com.demo.store.service.invoice;

import java.util.List;

import javax.transaction.Transactional;

import com.demo.store.entity.Invoice;
import com.demo.store.entity.Order;

import org.springframework.stereotype.Component;

@Component
@Transactional
public interface InvoiceBusinessService {
    
    public Invoice generateInvoice(Order order) throws Exception;
    public List<Invoice> getInvoicesByOrder(List<Order> orders) throws Exception;
}
