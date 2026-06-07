package com.substring.foodie.restaurant.controller;

import com.substring.foodie.restaurant.dto.RestaurantDto;
import com.substring.foodie.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public RestaurantDto createRestaurant(
            @RequestBody RestaurantDto restaurantDto
    ){
        RestaurantDto restaurantDto1 = restaurantService.create(restaurantDto);

        return restaurantDto1;
    }

    @PutMapping("/{id}")
    public RestaurantDto updateRestaurant(
            @RequestBody RestaurantDto restaurantDto,
            @PathVariable String id
    ){
        return restaurantService.update(restaurantDto,id);
    }

    @GetMapping("/{id}")
    public RestaurantDto getRestaurant(
            @PathVariable String id
    ){
        return restaurantService.getById(id);
    }

    @GetMapping("/search/{name}")
    public RestaurantDto getByName(
            @PathVariable String name
    ){
        return restaurantService.findByName(name);
    }

    @GetMapping
    public List<RestaurantDto> getAllRestaurants(){
        return restaurantService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(
            @PathVariable String id
    ){
        restaurantService.delete(id);
    }
}