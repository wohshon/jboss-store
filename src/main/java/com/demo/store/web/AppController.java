package com.demo.store.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.store.dto.CartItemDTO;
import com.demo.store.dto.CustomerDTO;
import com.demo.store.dto.ProductDTO;
import com.demo.store.entity.Cart;
import com.demo.store.entity.CartItem;
import com.demo.store.entity.Customer;
import com.demo.store.entity.Delivery;
import com.demo.store.entity.Invoice;
import com.demo.store.entity.Order;
import com.demo.store.entity.OrderItem;
import com.demo.store.entity.Product;
import com.demo.store.entity.status.OrderStatus;
import com.demo.store.service.DataService;
import com.demo.store.service.delivery.DeliveryBusinessService;
import com.demo.store.service.inventory.InventoryBusinessService;
import com.demo.store.service.invoice.InvoiceBusinessService;
import com.demo.store.service.order.OrderBusinessService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AppController {

    @Autowired
    DataService<Product> productDataService;

    @Autowired
    DataService<Order> orderDataService;

    @Autowired
    DataService<Customer> customerDataService;

    @Autowired
    DataService<Delivery> deliveryDataService;

    @Autowired
    DeliveryBusinessService deliveryBusinessService;

    @Autowired
    InventoryBusinessService inventoryBusinessService;

    @Autowired
    InvoiceBusinessService invoiceBusinessService;

    @Autowired
    OrderBusinessService orderBusinessService;

    @GetMapping("/login")
    public String login() {
        // simple routing
        return "login";
    }

    @PostMapping(path = "/home", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // @RequestMapping(path="/home",method = {RequestMethod.POST,
    // RequestMethod.GET}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView execLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("user {}", request.getParameter("inputEmail"));
        // check if customer is in db
        Customer c = new Customer();
        c.setEmail(request.getParameter("inputEmail"));
        c = customerDataService.getByEntityId(c);
        ModelAndView mv = new ModelAndView();
        if (c != null) {
            CustomerDTO cust = new CustomerDTO();
            cust.setEmail(c.getEmail());
            cust.setName(c.getName());
            cust.setDeliveryAddress(c.getAddress());
            cust.setContactInfo(c.getContact());
            session.setAttribute("SESS_USER", cust);
            List<Product> products = productDataService.getAll();
            session.setAttribute("SESS_PRODUCTS", products);
            // create new shopping cart since a login is done
            refreshCart(session);
            mv.setViewName("home");
        } else {
            mv.setViewName("login");
            mv.addObject("msg", "Login Error, User not found");
        }
        return mv;
    }

    @GetMapping("/home-direct")
    public String directHome() {
        return "home";
    }

    @PostMapping(path = "/checkout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView checkout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("checkout: {} {}", request.getParameter("deliveryAddress"), request.getParameter("contactInfo"));
        Cart cart = (Cart) session.getAttribute("SESS_CART");
        log.info("Checking out cart with ${} for {}", cart.getTotalPrice(), session.getAttribute("SESS_USER"));
        // call other services
        // create Order;
        ModelAndView mv = new ModelAndView("confirm");

        Order order = new Order();
        order.setName(request.getParameter("orderName"));
        try {
            order = orderBusinessService.generateOrder(order, cart);
        } catch (Exception e) {
            // should be not enough stock , //TODO, write a custom exception
            log.info("caucht exception when updating inventory");
            e.printStackTrace();
            mv.setViewName("home");
            mv.addObject("msg", "Error during order submission, your shopping cart items will be cleared");
            //clear and recreate cart
            session.removeAttribute("SESS_CART");
            refreshCart(session);
            return mv;            
        }

        log.info("saved Order {}", order.getName());
        try {
            Delivery del = new Delivery(); // place holder
            del.setDeliveryAddress(request.getParameter("deliveryAddress"));
            del.setContactInfo(request.getParameter("contactInfo"));
            del = deliveryBusinessService.raiseDeliveryRequest(del, order);
            log.info("saved Delivery {}", del.getName()); // could be detached, to check
            Invoice inv = invoiceBusinessService.generateInvoice(order);
            log.info("invoice generated {}", inv.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // invalidate cart after services are called

        session.removeAttribute("SESS_CART");
        return mv;
    }

    @GetMapping("/viewOrders")
    public ModelAndView viewOrders(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Customer c = new Customer();
        c.setEmail(((CustomerDTO) session.getAttribute("SESS_USER")).getEmail());
        c = customerDataService.getByEntityId(c);
        Object[] params = { "customer", c };
        List<Order> orders = orderDataService.query("Order.findByUsers", params);
        request.setAttribute("REQ_ORDERS", orders);

        Order[] orderParams = new Order[orders.size()];
        int i = 0;
        for (Order order : orders) {
            log.info("Orders {}", order.getName());
            orderParams[i++] = order;
        }
        Object[] searchParams = { "deliveryOrders", Arrays.asList(orderParams) };
        List<Delivery> deliveries = deliveryDataService.query("Delivery.findByOrder", searchParams);
        log.info("deliveries based on orders {}", deliveries);
        request.setAttribute("REQ_DELVS", deliveries);
        List<Invoice> invoices = new ArrayList<Invoice>();
        try {
            invoices = invoiceBusinessService.getInvoicesByOrder(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("invoice based on orders {}", invoices);
        request.setAttribute("REQ_INVOICES", invoices);

        ModelAndView mv = new ModelAndView("viewOrders");
        return mv;
    }

    @PostMapping(path="/updateCart",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateCart(@RequestBody CartItemDTO[] cartItemDTOs, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("Update Cart: {} items",cartItemDTOs.length);
        if (session.getAttribute("SESS_CART") == null ){
            refreshCart(session);
        }
        Cart cart = (Cart)session.getAttribute("SESS_CART");
        Map<String, CartItem> cartItemsMap =
            cart.getItems().stream().collect(Collectors.toMap(CartItem::getProductId, item -> item));
        for (CartItemDTO cartItemDTO : cartItemDTOs) {
            CartItem cartItem = cartItemsMap.get(cartItemDTO.getProduct().getProductId());
            if (cartItem != null) {
                cartItem.setQty(cartItem.getQty()+cartItemDTO.getQty());
            } else {
                cartItem = new CartItem();
                Product p = new Product();
                p.setProductId(cartItemDTO.getProduct().getProductId());
                cartItem.setProductId(p.getProductId());
                cartItem.setQty(cartItemDTO.getQty());
                cartItem.setItem(productDataService.getByEntityId(p));
                cart.addItem(cartItem);

            }
        }
        log.info("number of items in cart {}",cart.getItems().size());
        for (CartItem cartItem : cart.getItems()) {
            log.info("cart {} {} {} ",cartItem.getItem().getProductId(), cartItem.getQty(),cartItem.getItem().getPrice());
        }
        cart.refresh();
        Gson gson = new Gson();
        return gson.toJson(cart.getItems());
    }

    @GetMapping("/viewCart")
    public ModelAndView viewCart(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String deleteCart = request.getParameter("deleteCart");
        if (Boolean.parseBoolean(deleteCart)) {
            session.removeAttribute("SESS_CART");
            refreshCart(session);                        
        }
        ModelAndView mv = new ModelAndView("viewCart");
        return mv;
    }

    private void refreshCart(HttpSession session) {
        Cart cart = new Cart();
        Customer c = new Customer();
        c.setEmail(((CustomerDTO)session.getAttribute("SESS_USER")).getEmail());
        c = customerDataService.getByEntityId(c);
        cart.setCustomer(c);
        session.setAttribute("SESS_CART", cart);
    }
    @GetMapping("/test-order")
    public String testOrder (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        Customer c = new Customer();
        c.setEmail("johnd@gmail.com");
        c = customerDataService.getByEntityId(c);
        OrderItem i1 = new OrderItem();
        i1.setQty(1000);
        Product p1 = new Product();
        p1.setProductId("p0001");
        i1.setItem(productDataService.getByEntityId(p1));
        i1.setName(i1.getItem().getName());

        OrderItem i2 = new OrderItem();
        i2.setQty(1000);
        Product p4 = new Product();
        p4.setProductId("p0004");
        i2.setItem(productDataService.getByEntityId(p4));
        i2.setName(i2.getItem().getName());
        
        Order o = new Order();
        o.setName("Order 1");
        o.setCustomer(c);
        o.setStatus(OrderStatus.NEW);
        o.setOrderDate(new Date());
        i1.setOrder(o);
        i2.setOrder(o);
        o.getItems().add(i1);
        o.getItems().add(i2);
        orderDataService.save(o);

        //======================
         i1 = new OrderItem();
        i1.setQty(1000);
         p1 = new Product();
        p1.setProductId("p0001");
        i1.setItem(productDataService.getByEntityId(p1));
        i1.setName(i1.getItem().getName());

         i2 = new OrderItem();
        i2.setQty(1000);
         p4 = new Product();
        p4.setProductId("p0004");
        i2.setItem(productDataService.getByEntityId(p4));
        i2.setName(i2.getItem().getName());
        
         o = new Order();
        o.setName("Order 2");
        o.setStatus(OrderStatus.NEW);
        o.setCustomer(c);
        o.setOrderDate(new Date());
        i1.setOrder(o);
        i2.setOrder(o);
        o.getItems().add(i1);
        o.getItems().add(i2);
        orderDataService.save(o); 

        //get orders to test
        List<Order> list = orderDataService.getAll();
        for (Order order : list) {
            log.info("Order {} {} {}", order.getName(), order.getId(),order.getItems().size());
            for (OrderItem orderItem : order.getItems()) {
                log.info("-- items {} {}",orderItem.getName(),orderItem.getQty());

            }
        }

        return "test";
    }

    @GetMapping("/test-product")
    public String testProduct (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> products = productDataService.getAll();
        log.info("products size : {}",products.size());

        Product p = new Product();
        p.setId(4L);
       //p.setProductId("p0004");
        p = productDataService.get(p);
        log.info("product : {} {}",p.getId(), p.getName());
        p = new Product();
        p.setProductId("p0001");
        p = productDataService.getByEntityId(p);
        log.info("product by productId: {} {}",p.getId(), p.getName());
        
        
        p = productDataService.get(products.get(0));
        log.info("products by id : {} {}", p.getName(),p.getClass());
        p.setName("changed");
        productDataService.update(p);

        p = new Product();
        p.setDescription("hello");
        p.setName("Newbie");
        productDataService.save(p);

        //p = new Product();
        //p.setId(3L);
        //productDataService.remove(p);
        p = new Product();

        p.setId(4L);
         p = productDataService.get(p);
         log.info("products by id again: {} {}", p.getName(),p.getClass());
        //String[] params = {"name", "Tux Doll"};
        //List<Product> results = productDataService.query("Product.findByName", (Object[])params); 
        //log.info("products by name: {} {} ", results.size(), results.get(0)!=null?results.get(0).getName():"No result");

        String[] sarray = {"p0001","p0003"};
        Object[] params = {"ids", sarray };
        List<Product> results = productDataService.query("Product.findByProducts", params); 
        log.info("products by name: {} {} ", results.size(), results.get(0)!=null?results.get(0).getName():"No result");
        return "test";
    }

}
