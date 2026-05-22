package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.entity.Role;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/")
//    @ResponseBody
    public String testing(){
        System.out.println("getting user");
        return "user_list";
    }

    @RequestMapping("/player-list")
    public List<String> player(){
        List<String> list = new ArrayList<>();
        list.add("virat");
        list.add("rohit");
        list.add("dhoni");
        list.add("rahul");
        return list;
    }

    @RequestMapping("/get-user")
    public User getuser(){

        String template = null;
        template.length();

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("sachin");
        user.setEmail("sachin@gmail.com");
        user.setAvailable(true);
        user.setAddress("patel nagar");
        user.setRole(Role.ADMIN);
        user.setPhoneNumber("47483993229");
        user.setPassword("ak@123");
        return user;
    }

    @RequestMapping("/wish/{message}/for/{user}")
    public String wish(@PathVariable String message){
        return message;
    }

    @RequestMapping("/wish")
    public String wish2(@RequestParam(value = "message" , required = false , defaultValue = "ak")String usermessage , @RequestParam int id){
        return usermessage+id;
    }


}
