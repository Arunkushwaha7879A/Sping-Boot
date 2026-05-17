package com.jpa.example.course_jpa_example.repo;

import com.jpa.example.course_jpa_example.Repository.UserRepository;
import com.jpa.example.course_jpa_example.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepo {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void repoTest(){

        System.out.println("Testing repo");

//        Optional<User> userOptional = userRepository.findByEmailOrderByNameAsc("ak@gamil.com");
//
//        userOptional.ifPresentOrElse(user->{
//            System.out.println(user.getName());
//            System.out.println(user.getUserId());
//            System.out.println(user.getEmail());
//        },()->{
//            System.out.println("USER NOT FOUND!");
//        });

//by orelsethrow
//        User user = userOptional.orElseThrow(() -> new RuntimeException("user not found"));
//        System.out.println(user.getUserId());
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());



//        int i = userRepository.countByName("ankit");
//        System.out.println(i);
//
//        System.out.println(userRepository.existsByEmail("ak@gamil.com"));


//        List<User> a = userRepository.findByNameContaining("a");
//        a.forEach(u->{
//            System.out.println(u.getName());
//        });


    }

    @Test
    public void test2(){
        List<User> india = userRepository.getUserBycountry("usa");
        System.out.println(india.size());
    }
}
