package com.substring.foodie.food.controller;

import com.substring.foodie.food.dto.FoodItemDTO;
import com.substring.foodie.food.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public FoodItemDTO create(
            @RequestBody FoodItemDTO dto
    ){
        return foodItemService.create(dto);
    }

    @PutMapping("/{id}")
    public FoodItemDTO update(
            @RequestBody FoodItemDTO dto,
            @PathVariable String id
    ){
        return foodItemService.update(dto,id);
    }

    @GetMapping("/{id}")
    public FoodItemDTO getById(
            @PathVariable String id
    ){
        return foodItemService.getById(id);
    }

    @GetMapping
    public List<FoodItemDTO> getAll(){
        return foodItemService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<FoodItemDTO> getByCategory(
            @PathVariable String categoryId
    ){
        return foodItemService.getByCategory(categoryId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodItemDTO> getByRestaurant(
            @PathVariable String restaurantId
    ){
        return foodItemService.getByRestaurant(restaurantId);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ){
        foodItemService.delete(id);
    }
}