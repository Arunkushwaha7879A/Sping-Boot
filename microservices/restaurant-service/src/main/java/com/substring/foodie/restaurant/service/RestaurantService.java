package com.substring.foodie.restaurant.services;

import com.substring.foodie.restaurant.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {

    RestaurantDto create(RestaurantDto restaurantDto);

    RestaurantDto update(RestaurantDto restaurantDto,String id);

    RestaurantDto getById(String id);

    RestaurantDto findByName(String name);

    List<RestaurantDto> getAll();

    void delete(String id);

}