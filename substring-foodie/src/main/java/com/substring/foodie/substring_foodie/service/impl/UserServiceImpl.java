package com.substring.foodie.substring_foodie.service.impl;

import com.substring.foodie.substring_foodie.dto.UserDto;
import com.substring.foodie.substring_foodie.entity.Restaurant;
import com.substring.foodie.substring_foodie.entity.Role;
import com.substring.foodie.substring_foodie.entity.RoleEntity;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.exception.ResourceNotFoundException;
import com.substring.foodie.substring_foodie.repository.UserRepo;
import com.substring.foodie.substring_foodie.service.UserService;
import com.substring.foodie.substring_foodie.utils.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.util.BeanUtil;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        //GENERATE NEW ID FOR USER
        userDto.setId(Helper.generateRandomId());
        User user = convertUserDtotoUser(userDto);

        User savedUser = userRepo.save(user);
        //SAVE THE USER TO DATABASE
        return convertUsertoUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> userPage = userRepo.findAll(pageable);


        //return after converting to page<UserDto>
        return userPage.map((user)->convertUsertoUserDto(user));
    }

    @Override
    public List<UserDto> getUserByname(String userName) {
        return userRepo.findByName(userName).stream().map((user) -> convertUsertoUserDto(user)).toList();
    }

    @Override
    public UserDto getUserbyEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));

        return convertUsertoUserDto(user);
    }



    @Override
    public UserDto geUserbyId(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));

        return convertUsertoUserDto(user);
    }


    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> searchUserName(String keyword) {
        return List.of();
    }


    private User convertUserDtotoUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        //orrr
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPhoneNumber(userDto.getPhoneNumber());
//        user.setAddress(userDto.getAddress());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto convertUsertoUserDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        //orrr
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPhoneNumber(userDto.getPhoneNumber());
//        user.setAddress(userDto.getAddress());
//        user.setPassword(userDto.getPassword());
        return userDto;
    }





    //
//    //constructor injection
//    public UserServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public User saveUser(User user) {
//        user.setId(UUID.randomUUID().toString());
//        User saveEntity = userRepo.save(user);
//        return saveEntity;
//    }
//
//    @Transactional
//    public User updateUser(User user , String userId){
//        User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("not found"));
//
//        user1.setName(user.getName());
//
//
////        Restaurant restaurant3 = new Restaurant();
////        restaurant3.setId(UUID.randomUUID().toString());
////        restaurant3.setName("Dominos");
////        restaurant3.setAddress("Mumbai");
////        restaurant3.setIsOpen(false);
////
////        restaurant3.setUser(user1);
////
////        user1.getRestaurants().add(restaurant3);
//        User save = userRepo.save(user1);
//        return save;
//
//    }
//
//    @Override
//    public void testUserRole() {
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("Ayush singh");
//        user.setEmail("ayush@gmail.com");
//        user.setAddress("test address many to many");
//        user.setAvailable(true);
//        user.setPhoneNumber("9365328373");
//
//        RoleEntity roleEntity1 = new RoleEntity();
//        roleEntity1.setName("ROLE_ADMIN");
//
//
//        RoleEntity roleEntity2 = new RoleEntity();
//        roleEntity2.setName("ROLE_GUEST");
//
//        //link
//        //user ke trf se
//        user.getRoleEntities().add(roleEntity1);
//        user.getRoleEntities().add(roleEntity2);
//
//        //roleentity ke trf se
//        roleEntity1.getUsers().add(user);
//        roleEntity2.getUsers().add(user);
//
//        userRepo.save(user);
//        System.out.println("user saved");
//
//    }
}
