package com.mvc.example.spring_mvc_example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping("/home")
    public String home(){
        System.out.println("homepage");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("about page");
        return "about";
    }
}
