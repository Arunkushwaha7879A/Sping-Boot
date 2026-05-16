package com.first.controller;

import com.first.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginservice;

    @RequestMapping("/")
    public String loginpage(){
        System.out.println("this is login page");
        boolean islogin = loginservice.dologin();

        if (islogin){
            return "success_login";
        }else{
            return "login";
        }
    }


}
