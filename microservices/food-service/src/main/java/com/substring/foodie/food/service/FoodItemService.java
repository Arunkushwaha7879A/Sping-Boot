package com.substring.foodie.food.service;

import com.substring.foodie.food.dto.FoodItemDTO;

import java.util.List;

public interface FoodItemService {
    FoodItemDTO create(FoodItemDTO dto);

    FoodItemDTO update(FoodItemDTO dto,String id);

    FoodItemDTO getById(String id);

    List<FoodItemDTO> getAll();

    List<FoodItemDTO> getByCategory(String categoryId);

    List<FoodItemDTO> getByRestaurant(String restaurantId);

    void delete(String id);
}
