package com.substring.foodie.substring_foodie.service.impl;

import com.substring.foodie.substring_foodie.dto.FileData;
import com.substring.foodie.substring_foodie.dto.RestaurantDto;
import com.substring.foodie.substring_foodie.entity.Restaurant;
import com.substring.foodie.substring_foodie.exception.ResourceNotFoundException;
import com.substring.foodie.substring_foodie.repository.RestaurantRepo;
import com.substring.foodie.substring_foodie.service.FileService;
import com.substring.foodie.substring_foodie.service.RestaurantService;
import com.substring.foodie.substring_foodie.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Value("${restaurant.file.path}")
    private String bannerFolderPath;

    private RestaurantRepo restaurantRepo;
    private ModelMapper modelMapper;
    private FileService fileService;

    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, ModelMapper modelMapper, FileService fileService) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Override
    public RestaurantDto add(RestaurantDto restaurantDto) {
        restaurantDto.setId(Helper.generateRandomId()) ;
        restaurantDto.setCreatedDate(LocalDateTime.now());

        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        Restaurant saveEntity = restaurantRepo.save(restaurant);
        return modelMapper.map(saveEntity, RestaurantDto.class);
    }

    @Override
    public RestaurantDto update(RestaurantDto restaurantDto, String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found!!"));
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setIsOpen(restaurantDto.getIsOpen());
        restaurant.setOpenTime(restaurantDto.getOpenTime());
        restaurant.setCloseTime(restaurantDto.getCloseTime());
        Restaurant save = restaurantRepo.save(restaurant);
        return modelMapper.map(save, RestaurantDto.class);
    }

    @Override
    public void delete(String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found!!"));
        restaurantRepo.delete(restaurant);
    }

    @Override
    public RestaurantDto get(String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found!!"));
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public Page<RestaurantDto> getAll(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepo.findAll(pageable);
        return pageRestaurant.map(restaurant->modelMapper.map(restaurant,RestaurantDto.class));
    }

    @Override
    public List<RestaurantDto> searchByName(String keyword) {
        return restaurantRepo.findByNameContainingIgnoreCase(keyword).stream().map(restaurant -> modelMapper.map(restaurant,RestaurantDto.class)).toList();
    }

    @Override
    public Page<RestaurantDto> getOpenRestauants(Pageable pageable) {
        Page<Restaurant> pageRestaurant  = restaurantRepo.findByIsOpen(true, pageable);
        return pageRestaurant.map(restaurant->modelMapper.map(restaurant,RestaurantDto.class));
    }

    @Override
    public RestaurantDto uploadBanner(MultipartFile file, String id) throws IOException {


        //upload the file
        String FileName = file.getOriginalFilename();
        String fileExtension = FileName.substring(FileName.lastIndexOf("."));
        String newFileName = new Date().getTime() + fileExtension;
        FileData fileData = fileService.uploadFile(file, bannerFolderPath + newFileName);

        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found!!"));
        restaurant.setBanner(fileData.fileName());
        restaurantRepo.save(restaurant);

        return modelMapper.map(restaurant , RestaurantDto.class);
    }


}
