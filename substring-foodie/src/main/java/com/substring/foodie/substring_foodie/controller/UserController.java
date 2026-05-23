package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.dto.UserDto;
import com.substring.foodie.substring_foodie.entity.Role;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.exception.GlobalExceptionHandler;
import com.substring.foodie.substring_foodie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        UserDto userDtoResult = userService.saveUser(userDto);
        return new ResponseEntity<>(userDtoResult, HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResult);

    }

    @GetMapping
    public ResponseEntity<Page<UserDto> > findAll(
            @RequestParam(value = "page" , required = false , defaultValue = "0") int page,
            @RequestParam(value = "size" , required = false , defaultValue = "10") int size,
            @RequestParam(value = "sortBy" , required = false , defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "sortDir" , required = false , defaultValue = "desc") String  sortDir
    ){

        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);


        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") String id){
        return ResponseEntity.ok(userService.geUserbyId(id)) ;
    }

    //    @RequestMapping("/")
////    @ResponseBody
//    public String testing(){
//        System.out.println("getting user");
//        return "user_list";
//    }
//
//    @RequestMapping("/player-list")
//    public List<String> player(){
//        List<String> list = new ArrayList<>();
//        list.add("virat");
//        list.add("rohit");
//        list.add("dhoni");
//        list.add("rahul");
//        return list;
//    }
//
//    @RequestMapping("/get-user")
//    public User getuser(){
//
//        String template = null;
//        template.length();
//
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("sachin");
//        user.setEmail("sachin@gmail.com");
//        user.setAvailable(true);
//        user.setAddress("patel nagar");
//        user.setRole(Role.ADMIN);
//        user.setPhoneNumber("47483993229");
//        user.setPassword("ak@123");
//        return user;
//    }
//
//    @RequestMapping("/wish/{message}/for/{user}")
//    public String wish(@PathVariable String message){
//        return message;
//    }
//
//    @RequestMapping("/wish")
//    public String wish2(@RequestParam(value = "message" , required = false , defaultValue = "ak")String usermessage , @RequestParam int id){
//        return usermessage+id;
//    }

}
