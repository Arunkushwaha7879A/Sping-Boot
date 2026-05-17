package com.jpa.example.course_jpa_example.entities;

import com.jpa.example.course_jpa_example.dto.UserType;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name="jpa_user")
public class User {

    @Id
    @Column(name="jpa_user_id")
    private int userId;

    @Column(name="jpa_user_name" , nullable = false)
    private String name;
    private String email;
    private int age;
    private boolean isActive;

    //laptop:
    @OneToOne(mappedBy = "user" , cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    private Laptop laptop;

    @Transient
    private String extrainformation;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserType type = UserType.STUDENT;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getExtrainformation() {
        return extrainformation;
    }

    public void setExtrainformation(String extrainformation) {
        this.extrainformation = extrainformation;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
