package com.substring.foodie.substring_foodie.service;

import com.substring.foodie.substring_foodie.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {

    RestaurantDto add(RestaurantDto restaurantDto);

    RestaurantDto update(RestaurantDto restaurantDto , String id);

    void delete(String id);

    Page<RestaurantDto> getAll(Pageable pageable);

   RestaurantDto get(String id);

    List<RestaurantDto> searchByName(String keyword);

    Page<RestaurantDto> getOpenRestauants(Pageable pageable);

    RestaurantDto uploadBanner(MultipartFile file , String id) throws IOException;
}
