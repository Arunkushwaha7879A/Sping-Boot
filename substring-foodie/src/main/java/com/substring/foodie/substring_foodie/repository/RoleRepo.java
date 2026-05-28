package com.substring.foodie.substring_foodie.repository;

import com.substring.foodie.substring_foodie.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity , Integer> {

    RoleEntity findByName(String name);
}
