package com.substring.foodie.substring_foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foodie_users")
@Getter
@Setter
public class User {



    @Id
    private String Id;

    private String name;

    private String email;

    private String address;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role; //admin , cutomer , delivery_boy

    private boolean isAvailable = true; // applicable for DELIVERY_BOY

    private LocalDate createdDate;

    //Actual relation Restaurant entity ke user field se manage ho raha hai.
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<RoleEntity> roleEntities = new ArrayList<>();

    //save karne se pehle automatic createddate ke value change ho jaeegi
    @PrePersist
    public void preSave(){
        this.createdDate=LocalDate.now();
    }

    @PostPersist
    public void postSave(){
        System.out.println("entity saved: " + this.getId());
    }

    @PostUpdate
    public void postUpdate(){
        System.out.println("entity updated: " + this.getId());
    }

}
