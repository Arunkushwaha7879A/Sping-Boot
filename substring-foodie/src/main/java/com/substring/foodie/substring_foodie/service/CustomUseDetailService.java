package com.substring.foodie.substring_foodie.service;

import com.substring.foodie.substring_foodie.entity.User;
import com.substring.foodie.substring_foodie.exception.ResourceNotFoundException;
import com.substring.foodie.substring_foodie.repository.UserRepo;
import com.substring.foodie.substring_foodie.security.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUseDetailService implements UserDetailsService {


    private UserRepo userRepo;

    public CustomUseDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CustomUserDetail customUserDetail=new CustomUserDetail(user);

        return customUserDetail;
    }
}
