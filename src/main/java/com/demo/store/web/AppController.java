package com.demo.store.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

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
    ProductService productService;

    @ResponseBody
    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("In Controller {}","home");
        ModelAndView mv = new ModelAndView("index");
        productService.test();
        mv.addObject("msg", "Hello!");
        return mv;
        

    }


}
