package com.demo.store.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.store.dto.CartItemDTO;
import com.demo.store.dto.ProductDTO;
import com.demo.store.entity.Cart;
import com.demo.store.entity.CartItem;
import com.demo.store.entity.Product;
import com.demo.store.service.DataService;
import com.demo.store.service.ProductService;
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
    DataService<Product> productService;

    @GetMapping("/login")
    public String login() {
        //simple routing
        return "login";
    }

    @PostMapping(path="/home",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //@RequestMapping(path="/home",method = {RequestMethod.POST, RequestMethod.GET}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView execLogin( HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("user {}",request.getParameter("inputEmail"));
        session.setAttribute("SESS_USER", request.getParameter("inputEmail"));
        List<Product> products = productService.getAll();
        session.setAttribute("SESS_PRODUCTS", products);    
        //create new shopping cart since a login is done
        Cart cart = new Cart();
        session.setAttribute("SESS_CART", cart);
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @GetMapping("/home-direct")
    public String directHome() {
        return "home";
    }
    
    @PostMapping(path="/checkout",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView checkout( HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("checkout: {} {}",request.getParameter("deliveryAddress"),request.getParameter("contactInfo"));
        Cart cart = (Cart)session.getAttribute("SESS_CART");
        log.info("Checking out cart with ${} for {}", cart.getTotalPrice(), session.getAttribute("SESS_USER"));
        //call other services

        //invalidate cart after services are called
        session.removeAttribute("SESS_CART");
        ModelAndView mv = new ModelAndView("confirm");
        return mv;
    }



    @PostMapping(path="/updateCart",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateCart(@RequestBody CartItemDTO[] cartItemDTOs, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("Update Cart: {} items",cartItemDTOs.length);
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
                cartItem.setItem(productService.getByEntityId(p));
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

        ModelAndView mv = new ModelAndView("viewCart");
        return mv;
    }

    @GetMapping("/test")
    public String test (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> products = productService.getAll();
        log.info("products size : {}",products.size());

        Product p = productService.get(products.get(0));
        log.info("products by id : {} {}", p.getName(),p.getClass());
        p.setName("changed");
        productService.update(p);

        p = new Product();
        p.setDescription("hello");
        p.setName("Newbie");
        productService.add(p);

        p = new Product();
        p.setId(3L);
        productService.remove(p);
        p = new Product();

        p.setId(4L);
         p = productService.get(p);
         log.info("products by id again: {} {}", p.getName(),p.getClass());
        String[] params = {"name", "Tux Doll"};
        List<Product> results = productService.query("Product.findByName", (Object[])params); 
        log.info("products by name: {} {} ", results.size(), results.get(0)!=null?results.get(0).getName():"No result");

        return "test";
    }

}
