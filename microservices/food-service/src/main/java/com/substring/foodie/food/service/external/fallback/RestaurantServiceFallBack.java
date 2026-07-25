package com.substring.foodie.food.service.external.fallback;

import com.substring.foodie.food.dto.RestaurantDto;
import com.substring.foodie.food.service.external.ResataurantService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantServiceFallBack implements ResataurantService {
    @Override
    public RestaurantDto getById(String restaurantid) {
        System.out.println("fallback executed.....");
        return null;
    }

    @Override
    public List<RestaurantDto> getAll() {
        return List.of();
    }

    @Override
    public RestaurantDto create(RestaurantDto dto) {
        return null;
    }

    @Override
    public void delete(String restaurantId) {

    }
}
