package com.substring.foodie.substring_foodie.payload;

import jakarta.validation.constraints.*;

public class UserData {
    @NotEmpty(message="name is required!!")
    @Size(min=3 ,max=20 , message="Name must be between 2 and 20 chararcters")
    private String name;
    @Min(value = 18 , message = "minimum value required id 18")
    @Max(value = 99 , message = "maximum value required id 18")
    private int age;
    @Email(message = "invalid email !!")
    private String email;
    @NotEmpty(message = "password is required")
    private String password;

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
}
