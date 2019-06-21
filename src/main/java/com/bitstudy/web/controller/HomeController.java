package com.bitstudy.web.controller;

import com.bitstudy.web.common.util.Printer;
import com.bitstudy.web.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 */
@Controller
public class HomeController {

    @Autowired CustomerService customerService;
    @Autowired Printer p;

    @RequestMapping("/")
    public String index() {
        System.out.println("루트 URL 경로로 들어옴");

        // p.accept("람다");
        
        int count = customerService.countAll();
        System.out.println("고객의 총 인원 : "+count);
        // p.accept("람다2");
        return "index";
    }
    
}