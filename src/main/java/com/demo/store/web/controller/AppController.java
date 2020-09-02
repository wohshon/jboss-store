package com.demo.store.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.demo.store.entity.Cart;
import com.demo.store.entity.CartItem;
import com.demo.store.entity.Customer;
import com.demo.store.entity.Order;
import com.demo.store.entity.OrderItem;
import com.demo.store.entity.Product;
import com.demo.store.service.DataService;
import com.google.gson.Gson;


@Controller
public class AppController  {


	private DataService<Product> productService;
	private DataService<Order> orderService;
	private DataService<Customer> customerService;
	public ModelAndView about(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("about");
	}
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("Inside Controller****************home");
		//create shopping cart
		if (session.getAttribute("CART")==null) {
			Cart cart=new Cart();
			session.setAttribute("CART", cart);
		}
		ModelAndView mv=new ModelAndView("index");
		List<Product> products=productService.getAllEntities();
		int i=1;
		for (Product p: products) {
			mv.addObject("prod_"+i, p.getName());
			mv.addObject("shortDesc_"+i,p.getShortDescription());
			mv.addObject("prodId_"+i,p.getId());
			mv.addObject("prodPrice_"+i,p.getPrice());
			mv.addObject("img_"+i++, p.getImg());
		}
		mv.addObject("msg", "Welcome!!!");
		
		return mv;
	}
	
	@RequestMapping(path="/updateCart", method=RequestMethod.POST)
	@ResponseBody
	public String updateCart(@RequestBody CartItem cartItem, HttpSession session) {
		String response="";
		System.out.println(cartItem);
		//verify content
		System.out.println(cartItem.getItemId());		
		System.out.println(cartItem.getItemName());
		System.out.println(cartItem.getQty());
		System.out.println(cartItem.getItemPrice());
		System.out.println(cartItem.getLinePrice());
		cartItem.setLinePrice(cartItem.getItemPrice()*cartItem.getQty());
		
		
		//response=itemId+"-"+qty;
		
		//update cart
		Cart cart=(Cart)session.getAttribute("CART");
		cart.addItem(cartItem);
		cart.refresh();
		Gson gson=new Gson();
/*		
		Product p=new Product();
		p.setPrice(BigDecimal.valueOf(2000.50));
		p.setName("Red Fedora Robot");
		cartItem.setItem(p);
		 */
		//cartItem.setName(cartItem.getItemName());
		//cartItem.setItemPrice(itemPrice);
		session.setAttribute("CART", cart);
		//response=gson.toJson(cartItem);
		response=gson.toJson(cart);
		return response;
	}

	@RequestMapping(path="/checkOut", method=RequestMethod.POST)
	@ResponseBody
	public String checkOut(@RequestBody Customer customer , HttpSession session) {
		String response=null;
		System.out.print("custName " +customer.getName()+","+customer.getEmail());
		customer.setCustomerId(customer.getEmail());
		Gson gson=new Gson();
		Order order=new Order();
		//save customer
		//customer=customerService.add(customer);
/*		List<Customer> currentCustomer=customerService.query("BY_EMAIL",customer.getCustomerId());
		if (currentCustomer.size()==1) {
			
			customer=currentCustomer.get(0);
		} 
*/		
		order.setCustomer(customer);
		//hack
		order.setCustomerId(customer.getCustomerId());
		order.setOrderDate(new Date());
		order.setName("ORD_"+customer.getName());
		Cart cart=(Cart)session.getAttribute("CART");
		order.setTotalPrice(cart.getTotalPrice());
		//save customer to session
		session.setAttribute("CUST", customer);
		Iterator<CartItem> cartItems=cart.getItems().iterator();
		
		while (cartItems.hasNext()) {
			CartItem cartItem=cartItems.next();
			OrderItem orderItem=new OrderItem();
			orderItem.setName(cartItem.getItemName());
			Product product=new Product();
			product.setId(Long.valueOf(cartItem.getItemId()));
			orderItem.setProduct(product);
			orderItem.setQty(cartItem.getQty());
			orderItem.setLineCost(BigDecimal.valueOf(cartItem.getLinePrice()));
			orderItem.setOrder(order);
			order.getItems().add(orderItem);
			cartItems.remove();
		}
		orderService.add(order);
		//clear cart items
		//cart.setItems(new ArrayList<CartItem>());
		cart.setTotalPrice(BigDecimal.ZERO);
		session.setAttribute("CART", cart);
		return gson.toJson(customer);
	}

	@RequestMapping(path="/deleteItem", method=RequestMethod.POST)
	@ResponseBody
	public String deleteItem(@RequestBody String prodId, HttpSession session) {
		String response="";
/*		System.out.println(cartItem);
		//verify content
		System.out.println(cartItem.getItemId());		
		System.out.println(cartItem.getItemName());
		System.out.println(cartItem.getQty());
		System.out.println(cartItem.getItemPrice());
		System.out.println(cartItem.getLinePrice());
		cartItem.setLinePrice(cartItem.getItemPrice()*cartItem.getQty());
*/		
		
		//response=itemId+"-"+qty;
		
		//get cart
		Cart cart=(Cart)session.getAttribute("CART");
		CartItem deleteItem=new CartItem();
		deleteItem.setItemId(prodId);
		cart.removeItem(deleteItem);
		System.out.println("AppController deleted item... ");
		System.out.println(cart.getItems().size());
		cart.refresh();
		System.out.println(cart.getItems().size());
		Gson gson=new Gson();
/*		
		Product p=new Product();
		p.setPrice(BigDecimal.valueOf(2000.50));
		p.setName("Red Fedora Robot");
		cartItem.setItem(p);
		 */
		//cartItem.setName(cartItem.getItemName());
		//cartItem.setItemPrice(itemPrice);
		session.setAttribute("CART", cart);
		//response=gson.toJson(cartItem);
		response=gson.toJson(cart);
		return response;
	}
	
	
	public ModelAndView viewOrders(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mv=new ModelAndView("orders");
		Customer cust=(Customer)session.getAttribute("CUST");
		if (cust!=null) {
			List<Order> orders=orderService.query("BYCUSTOMER",cust.getCustomerId());
			System.out.println(orders.size());
			request.setAttribute("ORDERS", orders);
		}
		else {
			mv.addObject("msg", "Error retriving orders, please do a checkout first");

		}
		return mv;
	}


	@ExceptionHandler(HttpSessionRequiredException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="The session has expired")	
	public String handleSessionExpired(){		
	  return "sessionExpired";
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
		//customerService.add(cust);
	
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
