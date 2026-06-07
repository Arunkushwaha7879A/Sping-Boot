package com.substring.foodie.food.service.implementation;

import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.dto.FoodItemDTO;
import com.substring.foodie.food.entities.FoodCategory;
import com.substring.foodie.food.entities.FoodItem;
import com.substring.foodie.food.repository.FoodCategoryRepo;
import com.substring.foodie.food.repository.FoodItemRepo;
import com.substring.foodie.food.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepo foodItemRepository;
    private final FoodCategoryRepo foodCategoryRepository;

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

        response.setFoodCategoryDTO(categoryDTO);

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

        dto.setFoodCategoryDTO(categoryDTO);

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