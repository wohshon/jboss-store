package com.demo.store.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.store.entity.Customer;
import com.demo.store.entity.Order;
import com.demo.store.entity.Product;
import com.demo.store.service.DataService;



public class AppController extends MultiActionController {


	private DataService<Product> productService;
	private DataService<Order> orderService;
	private DataService<Customer> customerService;
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside Controller****************home");
		ModelAndView mv=new ModelAndView("index");
		List<Product> products=productService.getAllEntities();
		int i=1;
		for (Product p: products) {
			mv.addObject("prod_"+i, p.getName());
			mv.addObject("shortDesc_"+i,p.getShortDescription());
			mv.addObject("img_"+i++, p.getImg());
		}
		mv.addObject("msg", "Welcome!!!");
		
		return mv;
	}
	
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside Controller****************test:"+productService);
		ModelAndView mv=new ModelAndView("test");
		
		//check if cart is present
		//session.get
		//System.out.println(productService);
		List<Product> products=productService.getAllEntities();
		
		Product added=new Product();
		added.setName("new!!!");
		added.setPrice(BigDecimal.valueOf(1200.25));
		added=productService.add(added);
		
		Customer cust=new Customer();
		cust.setName("dude");
		cust.setEmail("dude@gmail.com");
		cust.setCustomerId(cust.getEmail());
		customerService.add(cust);
	
		Order order=new Order();
		order.setName("new order");
		order.setCustomer(cust);
		orderService.add(order);
		System.out.println("order "+order.getId());
		mv.addObject("msg", products.size());
		//mv.addObject("added", added.getId());
		
		return mv;
	}

	public DataService<Product> getProductService() {
		return productService;
	}

	public void setProductService(DataService<Product> productService) {
		this.productService = productService;
	}

	public DataService<Order> getOrderService() {
		return orderService;
	}

	public void setOrderService(DataService<Order> orderService) {
		this.orderService = orderService;
	}

	public DataService<Customer> getCustomerService() {
		return customerService;
	}

	public void setCustomerService(DataService<Customer> customerService) {
		this.customerService = customerService;
	}

	
	
}
