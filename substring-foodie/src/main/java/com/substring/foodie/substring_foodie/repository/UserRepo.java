package com.substring.foodie.substring_foodie.repository;

import com.substring.foodie.substring_foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
}
