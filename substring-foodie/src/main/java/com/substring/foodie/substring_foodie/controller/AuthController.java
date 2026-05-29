package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.dto.JwtResponse;
import com.substring.foodie.substring_foodie.dto.LoginRequest;
import com.substring.foodie.substring_foodie.dto.UserDto;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.repository.UserRepo;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationEntryPoint;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationFilter;
import com.substring.foodie.substring_foodie.security.JwtService;
import org.modelmapper.ModelMapper;
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
    private UserRepo userRepo;
    private ModelMapper modelMapper;


    public AuthController(ModelMapper modelMapper, UserRepo userRepo, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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


        UserDto userDto = modelMapper.map(userRepo.findByEmail(userDetails.getUsername()).get(), UserDto.class);

        JwtResponse build = JwtResponse.builder().token(jwtToken).user(userDto).build();
        return ResponseEntity.ok(build);
    }




}
