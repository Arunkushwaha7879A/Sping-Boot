package com.substring.foodie.substring_foodie.dto;

import com.substring.foodie.substring_foodie.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class JwtResponse {

    private String AccessToken;
    private String refreshToken;
    private UserDto user;

}
