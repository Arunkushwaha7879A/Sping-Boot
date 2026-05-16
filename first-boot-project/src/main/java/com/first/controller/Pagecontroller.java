package com.first.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Component - ni use kar sakte kyuki ye general bean banata hai ,par controller se jo bean banti hai wo webcontroller bean hoti hai , generally app ni use kar sakte
@Controller
@RequestMapping("/page")
public class Pagecontroller {

    //yeee methods ko bolte hai handlers-ye request ko handle kar rhe hai
    @RequestMapping("/about")
    public  String about(){
        System.out.println("rendering about page");
        return "about";
    }

}
