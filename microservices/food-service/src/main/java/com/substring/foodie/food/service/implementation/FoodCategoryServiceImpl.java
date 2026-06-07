package com.substring.foodie.food.service.implementation;

import com.substring.foodie.food.dto.FoodCategoryDTO;
import com.substring.foodie.food.entities.FoodCategory;
import com.substring.foodie.food.repository.FoodCategoryRepo;
import com.substring.foodie.food.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepo foodCategoryRepository;

    @Override
    public FoodCategoryDTO create(FoodCategoryDTO dto) {

        FoodCategory category = new FoodCategory();

        BeanUtils.copyProperties(dto, category);

        category.setId(UUID.randomUUID().toString());

        FoodCategory savedCategory =
                foodCategoryRepository.save(category);

        FoodCategoryDTO response = new FoodCategoryDTO();

        BeanUtils.copyProperties(savedCategory, response);

        return response;
    }

    @Override
    public FoodCategoryDTO update(FoodCategoryDTO dto, String id) {

        FoodCategory category =
                foodCategoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        FoodCategory updated =
                foodCategoryRepository.save(category);

        FoodCategoryDTO response = new FoodCategoryDTO();

        BeanUtils.copyProperties(updated, response);

        return response;
    }

    @Override
    public FoodCategoryDTO getById(String id) {

        FoodCategory category =
                foodCategoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Category not found"));

        FoodCategoryDTO dto = new FoodCategoryDTO();

        BeanUtils.copyProperties(category, dto);

        return dto;
    }

    @Override
    public List<FoodCategoryDTO> getAll() {

        return foodCategoryRepository.findAll()
                .stream()
                .map(category -> {

                    FoodCategoryDTO dto =
                            new FoodCategoryDTO();

                    BeanUtils.copyProperties(category, dto);

                    return dto;

                }).toList();
    }

    @Override
    public void delete(String id) {

        FoodCategory category =
                foodCategoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Category not found"));

        foodCategoryRepository.delete(category);
    }
}