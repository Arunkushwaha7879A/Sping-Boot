package com.substring.foodie.substring_foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="foodie_restaurant")
@Getter
@Setter
public class Restaurant {



    @Id
    private String Id;

    private String name;

    @Lob
    private String description;

    private String address;

    private LocalTime openTime;

    private LocalTime closeTime;

    private Boolean isOpen;

    private String banner;

    @ManyToOne
    private User user;
}