package com.jpa.example.course_jpa_example;

import com.jpa.example.course_jpa_example.Repository.UserRepository;
import com.jpa.example.course_jpa_example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CourseJpaExampleApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CourseJpaExampleApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setName("arun");
        user.setEmail("ak@gamil.com");
        user.setActive(true);
        user.setAge(20);

        User save = userRepository.save(user);
        System.out.println(save.getName());

        List<User> all = userRepository.findAll();
        all.forEach(user1 -> System.out.println(user1.getEmail()));


    }
}
