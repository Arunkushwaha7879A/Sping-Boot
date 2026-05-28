package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.dto.JwtResponse;
import com.substring.foodie.substring_foodie.dto.LoginRequest;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationEntryPoint;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationFilter;
import com.substring.foodie.substring_foodie.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){

        //created authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());

        //authenticating
        authenticationManager.authenticate(authentication);


        //getting token
        String jwtToken = jwtService.generateToken(loginRequest.email());
        //getting userdetail
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());

        JwtResponse build = JwtResponse.builder().token(jwtToken).build();
        return ResponseEntity.ok(build);
    }




}
