package com.substring.foodie.food.service.external;

import com.substring.foodie.food.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RESTAURANT_SERVICE" , url = "http://RESTAURANT-SERVICE")
public interface ResataurantService  {

    //get resaturant with given id:
    @GetMapping("/api/v1/restaurants/{id}")
    RestaurantDto getById(@PathVariable("id") String restaurantid);

    @GetMapping("/api/v1/restaurants")
    List<RestaurantDto> getAll();

    @PostMapping("/api/v1/restaurants")
    RestaurantDto create(@RequestBody RestaurantDto dto);

    @DeleteMapping("/api/v1/restaurants/{id}")
    void delete(@PathVariable("id") String restaurantId);
}
