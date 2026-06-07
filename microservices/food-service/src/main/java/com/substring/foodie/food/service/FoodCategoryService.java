package com.substring.foodie.food.service;

import com.substring.foodie.food.dto.FoodCategoryDTO;

import java.util.List;

public interface FoodCategoryService {
    FoodCategoryDTO create(FoodCategoryDTO dto);

    FoodCategoryDTO update(FoodCategoryDTO dto,String id);

    FoodCategoryDTO getById(String id);

    List<FoodCategoryDTO> getAll();

    void delete(String id);

}
