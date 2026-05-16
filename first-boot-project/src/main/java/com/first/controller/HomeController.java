package com.first.controller;

import com.first.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/magic")
    public String test() throws InterruptedException {
        Thread.sleep(1000);
        productService.createProduct();
        return "this is magic";
    }

    @RequestMapping("/another")
    public String test2(){
        productService.searchProduct();
        return "this is actually magic";
    }


}
