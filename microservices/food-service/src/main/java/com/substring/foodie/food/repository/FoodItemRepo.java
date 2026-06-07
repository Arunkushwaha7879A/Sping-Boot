package com.substring.foodie.food.repository;

import com.substring.foodie.food.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepo extends JpaRepository<FoodItem , String> {

    List<FoodItem> findByRestaurantId(String restaurantId);

    List<FoodItem> findByFoodCategoryId(String categoryId);

}
