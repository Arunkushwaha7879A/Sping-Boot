package com.substring.foodie.substring_foodie.repository;

import com.substring.foodie.substring_foodie.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface RestaurantRepo extends JpaRepository <Restaurant , String> {

    List<Restaurant> findByNameContainingIgnoreCase(String keyword);
    Page<Restaurant> findByIsOpen(boolean flag , Pageable pageable);
//    @Query("""
//SELECT r FROM Restaurant r
//WHERE :currentTime BETWEEN r.openTime AND r.closeTime
//""")
//    Page<Restaurant> findOpenRestaurants(
//            @Param("currentTime") LocalTime currentTime,
//            Pageable pageable
//    );
}
