package com.jpa.example.course_jpa_example;

import com.jpa.example.course_jpa_example.dto.UserType;
import com.jpa.example.course_jpa_example.entities.Address;
import com.jpa.example.course_jpa_example.entities.User;
import com.jpa.example.course_jpa_example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseJpaExampleApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}

    @Autowired
    private UserService userService;

    @Test
    public void testUser(){
        User user = new User();
        user.setUserId(44);
        user.setName("ashsih");
        user.setEmail("ankit@gamil.com");
        user.setActive(true);
        user.setAge(21);
        user.setType(UserType.STUDENT);

        Address address =new Address();
        address.setCountry("usa");
        address.setStreet("345/366 ashoka garden");
        address.setPinCode("454654");

        user.setAddress(address);

        User save = userService.save(user);
        System.out.println(save.getName());
    }

    @Test
    public void getUserTest(){
        User user = userService.get(1);
        System.out.println(user.getName());
    }

}
