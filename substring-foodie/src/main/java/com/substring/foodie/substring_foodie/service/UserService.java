package com.substring.foodie.substring_foodie.service;

import com.substring.foodie.substring_foodie.dto.UserDto;
import com.substring.foodie.substring_foodie.entity.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto , String userId);

    Page<UserDto> getAll(Pageable pageable);

    List<UserDto> getUserByname(String userName);

    UserDto getUserbyEmail(String mail);

    UserDto geUserbyId(String userId);

    void deleteUser(String userId);

    List<UserDto> searchUserName(String keyword);









}
