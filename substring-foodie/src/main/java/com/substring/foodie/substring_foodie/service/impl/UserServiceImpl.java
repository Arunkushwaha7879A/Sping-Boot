package com.substring.foodie.substring_foodie.service.impl;

import com.substring.foodie.substring_foodie.entity.Restaurant;
import com.substring.foodie.substring_foodie.entity.Role;
import com.substring.foodie.substring_foodie.entity.RoleEntity;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.repository.UserRepo;
import com.substring.foodie.substring_foodie.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    //constructor injection
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        User saveEntity = userRepo.save(user);
        return saveEntity;
    }

    @Transactional
    public User updateUser(User user , String userId){
        User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("not found"));

        user1.setName(user.getName());


//        Restaurant restaurant3 = new Restaurant();
//        restaurant3.setId(UUID.randomUUID().toString());
//        restaurant3.setName("Dominos");
//        restaurant3.setAddress("Mumbai");
//        restaurant3.setIsOpen(false);
//
//        restaurant3.setUser(user1);
//
//        user1.getRestaurants().add(restaurant3);
        User save = userRepo.save(user1);
        return save;

    }

    @Override
    public void testUserRole() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Ayush singh");
        user.setEmail("ayush@gmail.com");
        user.setAddress("test address many to many");
        user.setAvailable(true);
        user.setPhoneNumber("9365328373");

        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setName("ROLE_ADMIN");


        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setName("ROLE_GUEST");

        //link
        //user ke trf se
        user.getRoleEntities().add(roleEntity1);
        user.getRoleEntities().add(roleEntity2);

        //roleentity ke trf se
        roleEntity1.getUsers().add(user);
        roleEntity2.getUsers().add(user);

        userRepo.save(user);
        System.out.println("user saved");

    }
}
