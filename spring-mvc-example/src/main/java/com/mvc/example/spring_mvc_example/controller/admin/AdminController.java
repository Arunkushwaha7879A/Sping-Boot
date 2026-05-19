package com.mvc.example.spring_mvc_example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/home")
    public String adminhome(Model model){

        String title="Admin Home";
        List<String> userList = List.of("user1" , "user2" , "user3");

        //we added data to model
        model.addAttribute("title" , title);
        model.addAttribute("userList" , userList);
        model.addAttribute("currentDate" , LocalDate.now());


        return "admin/home";
    }
}
