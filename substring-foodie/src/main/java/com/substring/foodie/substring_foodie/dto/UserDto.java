package com.substring.foodie.substring_foodie.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String Id;

    private String name;

    private String email;

    private String address;

    private String password;

    private String phoneNumber;
}
