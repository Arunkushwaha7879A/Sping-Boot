package com.jpa.example.course_jpa_example.concep;

import com.jpa.example.course_jpa_example.Repository.LaptopRepo;
import com.jpa.example.course_jpa_example.Repository.UserRepository;
import com.jpa.example.course_jpa_example.entities.Laptop;
import com.jpa.example.course_jpa_example.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ConceptMain {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LaptopRepo laptopRepo;

//    @Test
//    public void testsaveOnetoOne(){
//        User user = userRepository.findById(34).orElseThrow(() -> new RuntimeException("user not found"));
//
//        Laptop laptop = new Laptop();
//        laptop.setModel("mac air");
//        laptop.setAbout("m1 chip");
//        user.setLaptop(laptop);
//        laptop.setUser(user);
//
//        laptopRepo.save(laptop);
//        System.out.println("laptop added");
//    }
//    @Test
//    public void getonetoone(){
//        User user = userRepository.findById(34).orElseThrow(() -> new RuntimeException("user not found"));
//
//        System.out.println(user.getName());
//        Laptop laptop = user.getLaptop();
//        System.out.println(laptop.getId());
//        System.out.println(laptop.getModel());
//    }

    @Test
    public void saveonetomany(){
        User user = userRepository.findById(34).orElseThrow(() -> new RuntimeException("not found"));

        Laptop laptop1 = new Laptop();
        laptop1.setModel("Macbook air");
        laptop1.setAbout("first latop of ankit");
        Laptop laptop2 = new Laptop();
        laptop2.setModel("Macbook pro");
        laptop2.setAbout("second latop of ankit");

        user.getLaptops().add(laptop1);
        user.getLaptops().add(laptop2);
        laptop1.setUser(user);
        laptop2.setUser(user);

        userRepository.save(user);

    }
}
