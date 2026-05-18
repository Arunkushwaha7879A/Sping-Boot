package com.substring.foodie.substring_foodie.service;

import com.substring.foodie.substring_foodie.entity.User;

public interface UserService {

    User saveUser(User user);
    User updateUser(User user , String userId);

    public void testUserRole();
}
