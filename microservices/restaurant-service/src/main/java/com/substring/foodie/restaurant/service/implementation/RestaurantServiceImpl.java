package com.substring.foodie.restaurant.service.implementation;

import com.substring.foodie.restaurant.dto.RestaurantDto;
import com.substring.foodie.restaurant.entities.Restaurant;
import com.substring.foodie.restaurant.repository.RestaurantRepository;
import com.substring.foodie.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDto create(RestaurantDto restaurantDto) {

        Restaurant restaurant = new Restaurant();

        BeanUtils.copyProperties(restaurantDto,restaurant);

        restaurant.setId(UUID.randomUUID().toString());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        RestaurantDto dto = new RestaurantDto();

        BeanUtils.copyProperties(savedRestaurant,dto);

        return dto;
    }

    @Override
    public RestaurantDto update(RestaurantDto restaurantDto, String id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setPictures(restaurantDto.getPictures());
        restaurant.setOpen(restaurantDto.isOpen());
        restaurant.setOpenTime(restaurantDto.getOpenTime());
        restaurant.setCloseTime(restaurantDto.getCloseTime());
        restaurant.setAboutRestaurant(restaurantDto.getAboutRestaurant());

        Restaurant updatedRestaurant =
                restaurantRepository.save(restaurant);

        RestaurantDto dto = new RestaurantDto();

        BeanUtils.copyProperties(updatedRestaurant,dto);

        return dto;
    }

    @Override
    public RestaurantDto getById(String id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        RestaurantDto dto = new RestaurantDto();

        BeanUtils.copyProperties(restaurant,dto);

        return dto;
    }

    @Transactional
    @Override
    public RestaurantDto findByName(String name) {

        Restaurant restaurant =
                restaurantRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        RestaurantDto dto = new RestaurantDto();

        BeanUtils.copyProperties(restaurant,dto);

        return dto;
    }

    @Override
    public List<RestaurantDto> getAll() {

        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> {

                    RestaurantDto dto = new RestaurantDto();

                    BeanUtils.copyProperties(restaurant,dto);

                    return dto;

                }).toList();
    }

    @Override
    public void delete(String id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantRepository.delete(restaurant);

    }
}