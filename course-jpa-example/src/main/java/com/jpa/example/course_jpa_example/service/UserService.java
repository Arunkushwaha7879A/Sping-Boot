package com.jpa.example.course_jpa_example.service;

import com.jpa.example.course_jpa_example.Repository.UserRepository;
import com.jpa.example.course_jpa_example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //save user
    public User save(User user){
        return userRepository.save(user);
    }

    public User get(int Userid){
        return userRepository.findById(Userid).orElseThrow(()->
            new RuntimeException("user not found")
        );
    }


}
