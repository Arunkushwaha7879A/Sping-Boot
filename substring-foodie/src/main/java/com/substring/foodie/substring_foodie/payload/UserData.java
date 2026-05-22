package com.substring.foodie.substring_foodie.payload;

import com.substring.foodie.substring_foodie.utils.ValidGender;
import jakarta.validation.constraints.*;

public class UserData {
    @NotEmpty(message="name is required!!")
    @Size(min=3 ,max=20 , message="Name must be between 2 and 20 chararcters")
    //must contain one caps letter
    //must contain one digit
    //must contsin one special character
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$" , message = "invalid username, username must contain 1 capital letter , one digit ,one charatert")
    private String name;
    @Min(value = 18 , message = "minimum value required id 18")
    @Max(value = 99 , message = "maximum value required id 99")
    private int age;
    @Email(message = "invalid email !!")
    private String email;
    @NotEmpty(message = "password is required")
    private String password;

    @ValidGender(message = "only male and female are allowed")
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
