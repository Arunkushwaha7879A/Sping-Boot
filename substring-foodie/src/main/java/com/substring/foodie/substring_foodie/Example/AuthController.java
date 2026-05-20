package com.substring.foodie.substring_foodie.Example;

import com.substring.foodie.substring_foodie.payload.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public void login(){

    }


    @RequestMapping("/signup")
    public String signup(@RequestBody List<String> Data){
//        logger.info("user name:{}" , userData.getName());
//        logger.info("age :{}" , userData.getAge());
//        logger.info("password :{}" , userData.getPassword());
//        logger.info("password :{}" , userData.getEmail());
        logger.info("name:{}" , Data.get(0));
        return "we got data";

    }

}
