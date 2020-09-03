package com.demo.store.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import com.demo.store.entity.Product;
import com.demo.store.service.DataService;
import com.demo.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class AppController {

    @Autowired
    DataService<Product> productService;

    @ResponseBody
    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("In Controller {}","home");
        ModelAndView mv = new ModelAndView("index");
        //productService.test();
        mv.addObject("msg", "Hello!");
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

        return "test";
    }

}
