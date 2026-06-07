package com.substring.foodie.food.controller;


import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @PostMapping
    public FoodCategoryDTO create(
            @RequestBody FoodCategoryDTO dto
    ){
        return foodCategoryService.create(dto);
    }

    @PutMapping("/{id}")
    public FoodCategoryDTO update(
            @RequestBody FoodCategoryDTO dto,
            @PathVariable String id
    ){
        return foodCategoryService.update(dto,id);
    }

    @GetMapping("/{id}")
    public FoodCategoryDTO getById(
            @PathVariable String id
    ){
        return foodCategoryService.getById(id);
    }

    @GetMapping
    public List<FoodCategoryDTO> getAll(){
        return foodCategoryService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ){
        foodCategoryService.delete(id);
    }

}
