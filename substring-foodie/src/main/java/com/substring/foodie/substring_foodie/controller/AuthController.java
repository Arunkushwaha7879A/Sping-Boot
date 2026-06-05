package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.dto.JwtResponse;
import com.substring.foodie.substring_foodie.dto.LoginRequest;
import com.substring.foodie.substring_foodie.dto.RefreshTokenRequest;
import com.substring.foodie.substring_foodie.dto.UserDto;
import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.repository.UserRepo;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationEntryPoint;
import com.substring.foodie.substring_foodie.security.JwtAuthenticationFilter;
import com.substring.foodie.substring_foodie.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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

        //getting userdetail
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());


        UserDto userDto = modelMapper.map(userRepo.findByEmail(userDetails.getUsername()).get(), UserDto.class);


        //getting token
        String jwtToken = jwtService.generateToken(loginRequest.email() , true);
        String refreshToken = jwtService.generateToken(loginRequest.email(), false);



        JwtResponse build = JwtResponse.builder()
                .AccessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userDto)
                .build();
        return ResponseEntity.ok(build);
    }
@PostMapping("/refresh-token")
    public ResponseEntity<?> responseToken(@RequestBody RefreshTokenRequest refreshTokenRequest){


        if(jwtService.validateToken(refreshTokenRequest.getRefreshToken()) && jwtService.isRefreshToken(refreshTokenRequest.getRefreshToken())){

            String usernameFromRefreshToken = jwtService.getUsername(refreshTokenRequest.getRefreshToken());
            UserDto userDto = modelMapper.map(userRepo.findByEmail(usernameFromRefreshToken).get(), UserDto.class);

            String accessToken = jwtService.generateToken(usernameFromRefreshToken, true);
            String newRefreshToken = jwtService.generateToken(usernameFromRefreshToken, false);

            JwtResponse response = JwtResponse.builder()
                    .AccessToken(accessToken)
                    .refreshToken(newRefreshToken)
                    .user(userDto)
                    .build();
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Refresh token !!");

        }
}




}
