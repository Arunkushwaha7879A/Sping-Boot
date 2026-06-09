package com.substring.foodie.food.service.implementation;

import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.dto.FoodItemDTO;
import com.substring.foodie.food.dto.RestaurantDto;
import com.substring.foodie.food.entities.FoodCategory;
import com.substring.foodie.food.entities.FoodItem;
import com.substring.foodie.food.repository.FoodCategoryRepo;
import com.substring.foodie.food.repository.FoodItemRepo;
import com.substring.foodie.food.service.FoodItemService;
import com.substring.foodie.food.service.external.ResataurantService;
import com.substring.foodie.food.service.external.RestWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {


    private final FoodItemRepo foodItemRepository;

    private final FoodCategoryRepo foodCategoryRepository;
    @Autowired
    private final  RestTemplate restTemplate;

    @Autowired
    private ResataurantService resataurantService;

    @Autowired
    private RestWebClientService restWebClientService;

    @Override
    public FoodItemDTO create(FoodItemDTO dto) {

        FoodCategory category =
                foodCategoryRepository.findById(dto.getFoodCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException("Category not found"));

        FoodItem foodItem = new FoodItem();

        BeanUtils.copyProperties(dto, foodItem);

        foodItem.setId(UUID.randomUUID().toString());

        foodItem.setFoodCategory(category);

        FoodItem savedFood =
                foodItemRepository.save(foodItem);

        FoodItemDTO response =
                new FoodItemDTO();

        BeanUtils.copyProperties(savedFood, response);

        response.setFoodCategoryId(
                savedFood.getFoodCategory().getId()
        );

        FoodCategoryDTO categoryDTO = new FoodCategoryDTO();

        categoryDTO.setId(savedFood.getFoodCategory().getId());
        categoryDTO.setName(savedFood.getFoodCategory().getName());
        categoryDTO.setDescription(savedFood.getFoodCategory().getDescription());

        response.setFoodCategory(categoryDTO);

        return response;
    }

    @Override
    public FoodItemDTO update(FoodItemDTO dto, String id) {

        FoodItem foodItem =
                foodItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Food Item not found"));

        FoodCategory category =
                foodCategoryRepository.findById(dto.getFoodCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException("Category not found"));

        foodItem.setTitle(dto.getTitle());
        foodItem.setDescription(dto.getDescription());
        foodItem.setQuantity(dto.getQuantity());
        foodItem.setOutOfStock(dto.isOutOfStock());
        foodItem.setFoodType(dto.getFoodType());
        foodItem.setRestaurantId(dto.getRestaurantId());
        foodItem.setFoodCategory(category);

        FoodItem updated =
                foodItemRepository.save(foodItem);

        FoodItemDTO response =
                new FoodItemDTO();

        BeanUtils.copyProperties(updated, response);

        response.setFoodCategoryId(
                updated.getFoodCategory().getId()
        );

        return response;
    }

    @Override
    public FoodItemDTO getById(String id) {

        FoodItem foodItem =
                foodItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Food Item not found"));

        FoodItemDTO dto =
                new FoodItemDTO();

        BeanUtils.copyProperties(foodItem, dto);

        dto.setFoodCategoryId(
                foodItem.getFoodCategory().getId()
        );

        FoodCategoryDTO categoryDTO = new FoodCategoryDTO();

        categoryDTO.setId(foodItem.getFoodCategory().getId());
        categoryDTO.setName(foodItem.getFoodCategory().getName());
        categoryDTO.setDescription(foodItem.getFoodCategory().getDescription());

        dto.setFoodCategory(categoryDTO);

        //RestTemplate-->
//        String restaurantServiceUrl="http://localhost:9091/api/v1/restaurants/"+foodItem.getRestaurantId();
//        String restaurantServiceUrl="lb://RESTAURANT-SERVICE/api/v1/restaurants/"+foodItem.getRestaurantId();
//
//        //calling another service
//        RestaurantDto restaurantDto = restTemplate.getForObject(restaurantServiceUrl, RestaurantDto.class);
//
//        dto.setRestaurant(restaurantDto);

        //feign client-->
//        RestaurantDto restaurantDto = resataurantService.getById(foodItem.getRestaurantId());

        RestaurantDto restaurantDto = restWebClientService.getById(foodItem.getRestaurantId());


        dto.setRestaurant(restaurantDto);



        return dto;
    }

    @Override
    public List<FoodItemDTO> getAll() {

        return foodItemRepository.findAll()
                .stream()
                .map(food -> {

                    FoodItemDTO dto =
                            new FoodItemDTO();

                    BeanUtils.copyProperties(food, dto);

                    dto.setFoodCategoryId(
                            food.getFoodCategory().getId()
                    );
                    FoodCategoryDTO categoryDTO = new FoodCategoryDTO();

                    categoryDTO.setId(food.getFoodCategory().getId());
                    categoryDTO.setName(food.getFoodCategory().getName());
                    categoryDTO.setDescription(food.getFoodCategory().getDescription());

                    dto.setFoodCategory(categoryDTO);

                    return dto;

                }).toList();
    }

    @Override
    public List<FoodItemDTO> getByCategory(String categoryId) {

        return foodItemRepository
                .findByFoodCategoryId(categoryId)
                .stream()
                .map(food -> {

                    FoodItemDTO dto =
                            new FoodItemDTO();

                    BeanUtils.copyProperties(food, dto);

                    dto.setFoodCategoryId(
                            food.getFoodCategory().getId()
                    );

                    return dto;

                }).toList();
    }

    @Override
    public List<FoodItemDTO> getByRestaurant(String restaurantId) {

        return foodItemRepository
                .findByRestaurantId(restaurantId)
                .stream()
                .map(food -> {

                    FoodItemDTO dto =
                            new FoodItemDTO();

                    BeanUtils.copyProperties(food, dto);

                    dto.setFoodCategoryId(
                            food.getFoodCategory().getId()
                    );

                    return dto;

                }).toList();
    }

    @Override
    public void delete(String id) {

        FoodItem foodItem =
                foodItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Food Item not found"));

        foodItemRepository.delete(foodItem);
    }
}