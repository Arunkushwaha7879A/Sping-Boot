package com.substring.foodie.substring_foodie.services;

import com.substring.foodie.substring_foodie.entity.Restaurant;
import com.substring.foodie.substring_foodie.entity.Role;
import com.substring.foodie.substring_foodie.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UserService {
    @Autowired
    private com.substring.foodie.substring_foodie.service.UserService userService;

    @Test
    public void testSaveUser(){
//        System.out.println("saving user");
//
//        User user = new User();
//        user.setName("Ankush kumar");
//        user.setEmail("ankus@gmail.com");
//        user.setAddress("test address , delhi");
//        user.setRole(Role.ADMIN);
//        user.setAvailable(true);
//        user.setPhoneNumber("9365328373");
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setId(UUID.randomUUID().toString());
//        restaurant.setName("KFC");
//        restaurant.setAddress("Mumbai");
//        restaurant.setIsOpen(true);
//
//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setId(UUID.randomUUID().toString());
//        restaurant1.setName("Burger king");
//        restaurant1.setAddress("Mumbai");
//        restaurant1.setIsOpen(true);
//
//        restaurant1.setUser(user);
//        restaurant.setUser(user);
//
//        user.getRestaurants().add(restaurant1);
//        user.getRestaurants().add(restaurant);
//
//        User user1 = userService.saveUser(user);
//        System.out.println(user1.getName());

    }

    @Test
    public void testUpdate(){
//        User user = userService.updateUser();
//        System.out.println("updated");

        userService.testUserRole();

    }
}
